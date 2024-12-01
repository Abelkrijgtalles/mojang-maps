@echo off & setlocal enabledelayedexpansion

echo ==================== Note: All build jars will be in the folder called 'buildAllJars' ====================
del /F /Q buildAllJars\original\*
del /F /Q buildAllJars\*
mkdir buildAllJars
mkdir buildAllJars\original

@rem Loop trough everything in the version properties folder
for %%f in (versionProperties\*) do (
    @rem Get the name of the version that is going to be compiled
    set version=%%~nf

    @rem Clean out the folders, build it, and merge it
    echo ==================== Cleaning workspace to build !version! ====================
    call .\gradlew.bat clean -PmcVer="!version!" --no-daemon
    echo ==================== Building !version! ====================
    call .\gradlew.bat build -PmcVer="!version!" --no-daemon
    echo ==================== Merging !version! ====================
    call .\gradlew.bat mergeJars -PmcVer="!version!" --no-daemon
    echo ==================== Moving Merged jar ====================
    move Merged\*.jar buildAllJars\
)

echo ================================================================
echo =========================== FINISHED ===========================
echo ================================================================

endlocal
