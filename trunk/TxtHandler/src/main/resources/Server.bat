@echo off

rem don't modify the caller's environment
rem setlocal

rem Set up prog to be the path of this script, including following symlinks,
rem and set up progdir to be the fully-qualified pathname of its directory.
set prog=%~f0

rem Change current directory and drive to where the script is, to avoid
rem issues with directories containing whitespaces.
cd /d %~dp0

rem Check we have a valid Java.exe in the path.
set java_exe=
call find_java.bat
if not defined java_exe goto :EOF

set jarfile=com.mycompany.txthandler.NewClass1


if exist %jarfile% goto JarFileOk
    echo 文件%jarfile%已找到

:JarFileOk

set jarpath=%frameworkdir%%jarfile%

call %java_exe% %jarpath% %*
pause
exit
echo
