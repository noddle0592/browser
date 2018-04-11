@echo off
setlocal enabledelayedexpansion
set libdir=../lib
set cp=.
for %%j in (%libdir%/*.jar) do set cp=!cp!;%libdir%/%%j
java -cp %cp% com.tone.gf.GFMainFrame