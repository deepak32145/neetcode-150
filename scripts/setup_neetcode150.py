"""Copy NeetCode 150 Java solutions from upstream repo into src/main/java."""
from __future__ import annotations

import re
import shutil
from pathlib import Path

ROOT = Path(__file__).resolve().parents[1]
UPSTREAM_JAVA = ROOT / "_upstream" / "java"
DEST = ROOT / "src" / "main" / "java" / "neetcode150"

# Official NeetCode 150 (leetcode.com/problem-list/plakya4j/) — LeetCode numbers by category
NEETCODE_150: dict[str, list[int]] = {
    "01-arrays-hashing": [217, 242, 1, 49, 347, 238, 36, 271, 128],
    "02-two-pointers": [125, 167, 15, 11, 42],
    "03-stack": [20, 155, 150, 22, 739, 853, 84],
    "04-sliding-window": [121, 3, 424, 567, 76, 239],
    "05-binary-search": [704, 74, 875, 153, 33, 981, 4],
    "06-linked-list": [206, 21, 141, 143, 19, 138, 2, 287, 146, 23, 25],
    "07-trees": [226, 104, 543, 110, 100, 572, 235, 102, 199, 1448, 98, 230, 105, 124, 297],
    "08-tries": [208, 211, 212],
    "09-heap": [703, 1046, 973, 215, 621, 355, 295],
    "10-backtracking": [78, 39, 40, 46, 90, 79, 131, 17, 51],
    "11-graphs": [
        200, 695, 133, 286, 994, 417, 130, 207, 210, 261, 323, 684, 127,
        332, 1584, 743, 778, 269, 787,
    ],
    "12-dynamic-programming": [
        70, 746, 198, 213, 5, 647, 91, 322, 152, 139, 300, 416, 62, 1143, 309,
        518, 494, 97, 329, 115, 72, 312, 10,
    ],
    "13-intervals": [57, 56, 435, 252, 253, 1851],
    "14-greedy": [53, 55, 45, 134, 846, 1899, 763, 678],
    "15-math-geometry": [48, 54, 73, 202, 66, 50, 43, 2013],
    "16-bit-manipulation": [136, 191, 338, 190, 268, 371, 7],
}


def find_upstream_file(num: int) -> Path | None:
    prefix = f"{num:04d}-"
    matches = sorted(UPSTREAM_JAVA.glob(f"{prefix}*.java"))
    if not matches:
        return None
    # Prefer lowercase slug filenames when duplicates exist (Windows checkout)
    matches.sort(key=lambda p: (p.name != p.name.lower(), p.name))
    return matches[0]


def add_package(content: str, package: str, class_name: str) -> str:
    if content.lstrip().startswith("package "):
        return re.sub(r"^package\s+[\w.]+;", f"package {package};", content, count=1, flags=re.MULTILINE)
    return f"package {package};\n\n{content}"


def main() -> None:
    if not UPSTREAM_JAVA.is_dir():
        raise SystemExit(f"Missing upstream java folder: {UPSTREAM_JAVA}")

    all_nums = [n for nums in NEETCODE_150.values() for n in nums]
    assert len(all_nums) == 150, len(all_nums)

    missing: list[int] = []
    copied = 0

    for category, nums in NEETCODE_150.items():
        pkg = f"neetcode150.{category.replace('-', '_')}"
        cat_dir = DEST / category
        cat_dir.mkdir(parents=True, exist_ok=True)

        for num in nums:
            src = find_upstream_file(num)
            if src is None:
                missing.append(num)
                continue

            slug = src.stem.split("-", 1)[1]
            dest_name = f"{num:04d}_{slug}.java"
            dest = cat_dir / dest_name

            text = src.read_text(encoding="utf-8")
            text = add_package(text, pkg, "Solution")
            dest.write_text(text, encoding="utf-8")
            copied += 1

    manifest = ROOT / "NEETCODE_150.txt"
    lines = ["# NeetCode 150 — https://leetcode.com/problem-list/plakya4j/", ""]
    for category, nums in NEETCODE_150.items():
        lines.append(f"## {category}")
        for num in nums:
            lines.append(f"{num:4d}  https://leetcode.com/problems/?search={num}")
        lines.append("")
    manifest.write_text("\n".join(lines), encoding="utf-8")

    print(f"Copied {copied}/150 from upstream.")
    if missing:
        print("Missing upstream Java (need manual solutions):", missing)


if __name__ == "__main__":
    main()
