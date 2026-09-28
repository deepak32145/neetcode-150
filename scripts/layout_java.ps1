$Root = (Resolve-Path (Join-Path $PSScriptRoot "..")).Path
$Src = Join-Path $Root "src\main\java\neetcode150"
$Dest = Join-Path $Src "problems"

$utilTypes = @(
    'HashMap','HashSet','ArrayList','LinkedList','PriorityQueue','Deque','Queue',
    'Stack','Map','Set','List','Collections','Arrays','Comparator'
)

function Get-ProblemNumber($fileName) {
    if ($fileName -match '^(\d{4})_') { return [int]$Matches[1] }
    return $null
}

function Keep-FirstSolutionBlock($content) {
    if ($content -notmatch '(?m)class\s+Solution\d*\b') {
        return $content
    }
    $idx = [regex]::Match($content, '(?m)^\s*(?:public\s+)?class\s+Solution\d*\b').Index
    if ($idx -lt 0) { return $content }
    $rest = $content.Substring($idx)
    $matches = [regex]::Matches($rest, '(?m)^\s*(?:public\s+)?class\s+Solution\d*\b')
    if ($matches.Count -gt 1) {
        $rest = $rest.Substring(0, $matches[1].Index).TrimEnd()
    }
    $rest = [regex]::Replace($rest, 'class\s+Solution1\b', 'class Solution', 1)
    return $rest
}

function Ensure-SolutionWrapper($content) {
    $trim = $content.Trim()
    if ($trim -match '(?m)^\s*(?:public\s+)?class\s+') { return $content }
    return "class Solution {`n`n$trim`n`n}"
}

function Get-MainClassName($content) {
    $m = [regex]::Match($content, '(?m)^\s*public\s+class\s+(\w+)')
    if ($m.Success) { return $m.Groups[1].Value }
    $m = [regex]::Match($content, '(?m)^\s*class\s+(\w+)')
    if ($m.Success) { return $m.Groups[1].Value }
    return 'Solution'
}

function Add-Imports($body) {
    $imports = New-Object System.Collections.Generic.List[string]
    if ($body -match '\bListNode\b' -and $body -notmatch 'import neetcode150.common.ListNode') {
        $imports.Add('import neetcode150.common.ListNode;')
    }
    if ($body -match '\bTreeNode\b' -and $body -notmatch 'import neetcode150.common.TreeNode') {
        $imports.Add('import neetcode150.common.TreeNode;')
    }
    foreach ($t in $utilTypes) {
        if ($body -match "\b$t\b") {
            if ($body -notmatch 'import java.util') {
                $imports.Add('import java.util.*;')
            }
            break
        }
    }
    if ($imports.Count -eq 0) { return $body }
    return ($imports -join "`n") + "`n`n" + $body
}

if (Test-Path $Dest) { Remove-Item -Recurse -Force $Dest }
New-Item -ItemType Directory -Force -Path $Dest | Out-Null

$files = Get-ChildItem -Path $Src -Recurse -Filter "*.java" |
    Where-Object { $_.FullName -notmatch '\\common\\|\\tools\\|\\problems\\' }

foreach ($f in $files) {
    $num = Get-ProblemNumber $f.Name
    if (-not $num) { continue }

    $body = Get-Content $f.FullName -Raw -Encoding UTF8
    $body = $body -replace '(?m)^package\s+[\w.]+;\s*', ''
    $body = Keep-FirstSolutionBlock $body
    $body = Ensure-SolutionWrapper $body
    $body = Add-Imports $body

    $className = Get-MainClassName $body
    $pkg = "neetcode150.problems.p$num"
    $dir = Join-Path $Dest "p$num"
    New-Item -ItemType Directory -Force -Path $dir | Out-Null

    $out = "package $pkg;`n`n" + $body.Trim() + "`n"
    $outFile = Join-Path $dir "$className.java"
    Set-Content -Path $outFile -Value $out -Encoding UTF8 -NoNewline
}

# Remove legacy flat category folders
Get-ChildItem -Path $Src -Directory |
    Where-Object { $_.Name -match '^\d{2}-' } |
    ForEach-Object { Remove-Item -Recurse -Force $_.FullName }

Write-Host "Laid out $($files.Count) problems under problems/p####/"
