$Root = (Resolve-Path (Join-Path $PSScriptRoot "..")).Path
$UpstreamJava = Join-Path $Root "_upstream\java"
$Dest = Join-Path $Root "src\main\java\neetcode150"

$Categories = [ordered]@{
    "01-arrays-hashing" = @(217,242,1,49,347,238,36,271,128)
    "02-two-pointers" = @(125,167,15,11,42)
    "03-stack" = @(20,155,150,22,739,853,84)
    "04-sliding-window" = @(121,3,424,567,76,239)
    "05-binary-search" = @(704,74,875,153,33,981,4)
    "06-linked-list" = @(206,21,141,143,19,138,2,287,146,23,25)
    "07-trees" = @(226,104,543,110,100,572,235,102,199,1448,98,230,105,124,297)
    "08-tries" = @(208,211,212)
    "09-heap" = @(703,1046,973,215,621,355,295)
    "10-backtracking" = @(78,39,40,46,90,79,131,17,51)
    "11-graphs" = @(200,695,133,286,994,417,130,207,210,261,323,684,127,332,1584,743,778,269,787)
    "12-dynamic-programming" = @(70,746,198,213,5,647,91,322,152,139,300,416,62,1143,309,518,494,97,329,115,72,312,10)
    "13-intervals" = @(57,56,435,252,253,1851)
    "14-greedy" = @(53,55,45,134,846,1899,763,678)
    "15-math-geometry" = @(48,54,73,202,66,50,43,2013)
    "16-bit-manipulation" = @(136,191,338,190,268,371,7)
}

function Find-UpstreamFile($num) {
    $prefix = "{0:D4}-" -f $num
    $matches = Get-ChildItem -Path $UpstreamJava -Filter "$prefix*.java" -ErrorAction SilentlyContinue
    if (-not $matches) { return $null }
    return ($matches | Sort-Object Name | Select-Object -First 1)
}

function Add-Package($content, $package) {
    if ($content -match '(?m)^\s*package\s+[\w.]+;') {
        return [regex]::Replace($content, '(?m)^package\s+[\w.]+;', "package $package;", 1)
    }
    return "package $package;`n`n$content"
}

$missing = @()
$copied = 0

foreach ($entry in $Categories.GetEnumerator()) {
    $category = $entry.Key
    $nums = $entry.Value
    $pkg = "neetcode150." + ($category -replace '-', '_')
    $catDir = Join-Path $Dest $category
    New-Item -ItemType Directory -Force -Path $catDir | Out-Null

    foreach ($num in $nums) {
        $src = Find-UpstreamFile $num
        if (-not $src) {
            $missing += $num
            continue
        }
        $slug = $src.BaseName.Substring(5)
        $destFile = Join-Path $catDir ("{0:D4}_{1}.java" -f $num, $slug)
        $text = Get-Content -Path $src.FullName -Raw -Encoding UTF8
        Set-Content -Path $destFile -Value $text -Encoding UTF8 -NoNewline
        $copied++
    }
}

Write-Host "Copied $copied/150 from upstream."
if ($missing.Count -gt 0) {
    Write-Host "Missing:" ($missing -join ', ')
}
