# Contributing to Mojang Maps

Hi, I'm really grateful for wanting to help me out with Mojang Maps. It's mostly a solo project, and contributing
doesn't only help and fix my shitty code, but it also gives me just motivation to work on this project.

## How to clone

You can do it in 2 ways:
If you've already cloned the repo, follow way 2. If not, follow way 1.

Way 1: Clone the repo
with `--recurse-submodules` (`git clone --recurse-submodules https://github.com/Abelkrijgtalles/mojang-maps.git`).

Way 2: Assuming you've already cloned the repo and are inside of it, run `git submodule update --init`.

## How to build

Assuming you have maven installed, just run `mvn clean install package`.

Your JAR will be in the `target`-folder, named `MojangMaps-[version].jar`.

## Some other stuff

If you want, you can make a discussion to just show off what you've been working on, asking for help, or something else.
Note: Issues have to be reported in issues and do not belong in issues.