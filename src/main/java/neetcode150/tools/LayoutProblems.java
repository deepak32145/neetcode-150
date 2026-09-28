package neetcode150.tools;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
 * One-time / refresh helper: moves category flat files into {@code problems/p####/Solution.java}.
 * Run: {@code mvn -q exec:java -Dexec.mainClass=neetcode150.tools.LayoutProblems}
 * (requires exec-maven-plugin) or run main from the IDE after {@code mvn compile}.
 */
public final class LayoutProblems {

    private static final Pattern PROBLEM_NUM = Pattern.compile("(\\d{4})_");
    private static final Pattern SECOND_SOLUTION = Pattern.compile("(?m)^\\s*//.*\\n|^\\s*class\\s+Solution\\b");

    private LayoutProblems() {}

    public static void main(String[] args) throws IOException {
        Path root = Path.of(System.getProperty("user.dir"));
        Path srcRoot = root.resolve("src/main/java/neetcode150");
        Path legacyRoot = srcRoot;
        Path destRoot = srcRoot.resolve("problems");

        try (Stream<Path> walk = Files.walk(legacyRoot)) {
            walk.filter(p -> p.toString().endsWith(".java"))
                    .filter(p -> !p.toString().contains("common" + java.io.File.separator))
                    .filter(p -> !p.toString().contains("tools" + java.io.File.separator))
                    .filter(p -> !p.toString().contains("problems" + java.io.File.separator))
                    .forEach(p -> {
                        try {
                            layoutOne(p, destRoot);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });
        }

        System.out.println("Layout complete under " + destRoot);
    }

    private static void layoutOne(Path source, Path destRoot) throws IOException {
        String name = source.getFileName().toString();
        Matcher m = PROBLEM_NUM.matcher(name);
        if (!m.find()) {
            return;
        }
        int num = Integer.parseInt(m.group(1));
        String pkg = "neetcode150.problems.p" + num;
        Path dir = destRoot.resolve("p" + num);
        Files.createDirectories(dir);

        String body = Files.readString(source, StandardCharsets.UTF_8);
        body = keepFirstSolutionOnly(body);
        body = stripPackage(body);
        body = ensureImports(body);

        String out = "package " + pkg + ";\n\n" + body.trim() + "\n";
        Files.writeString(dir.resolve("Solution.java"), out, StandardCharsets.UTF_8);
    }

    private static String keepFirstSolutionOnly(String content) {
        int first = indexOfSolutionClass(content);
        if (first < 0) {
            return content;
        }
        String rest = content.substring(first);
        Matcher m = Pattern.compile("(?m)^class\\s+Solution\\b").matcher(rest);
        if (!m.find()) {
            return rest;
        }
        int firstEnd = m.end();
        if (m.find(firstEnd)) {
            rest = rest.substring(0, m.start()).trim();
        }
        return rest;
    }

    private static int indexOfSolutionClass(String content) {
        Matcher m = Pattern.compile("(?m)^class\\s+Solution\\b").matcher(content);
        return m.find() ? m.start() : -1;
    }

    private static String stripPackage(String content) {
        return content.replaceFirst("(?m)^package\\s+[\\w.]+;\\s*", "");
    }

    private static String ensureImports(String body) {
        StringBuilder imports = new StringBuilder();
        if (body.contains("ListNode") && !body.contains("import neetcode150.common.ListNode")) {
            imports.append("import neetcode150.common.ListNode;\n");
        }
        if (body.contains("TreeNode") && !body.contains("import neetcode150.common.TreeNode")) {
            imports.append("import neetcode150.common.TreeNode;\n");
        }
        if (needsJavaUtil(body)) {
            imports.append("import java.util.*;\n");
        }
        if (imports.length() == 0) {
            return body;
        }
        return imports + "\n" + body;
    }

    private static boolean needsJavaUtil(String body) {
        String[] types = {
            "HashMap", "HashSet", "ArrayList", "LinkedList", "PriorityQueue", "Deque",
            "Queue", "Stack", "Map", "Set", "List", "Collections", "Arrays", "Comparator"
        };
        for (String t : types) {
            if (body.contains(t)) {
                return true;
            }
        }
        return false;
    }
}
