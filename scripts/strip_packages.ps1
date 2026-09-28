$Root = (Resolve-Path (Join-Path $PSScriptRoot "..")).Path
$javaRoot = Join-Path $Root "src\main\java\neetcode150"
Get-ChildItem -Path $javaRoot -Recurse -Filter "*.java" | ForEach-Object {
    $content = Get-Content $_.FullName -Raw -Encoding UTF8
    $content = [regex]::Replace($content, '(?m)^package\s+[\w.]+;\s*\r?\n', '')
    Set-Content -Path $_.FullName -Value $content -Encoding UTF8 -NoNewline
}
Write-Host "Stripped package declarations from all solution files."
