from subprocess import call

try:
	call(["mkdir", "bin/"])
except:
	pass

call(["javac", "-d", "bin", "src/mateuspires/so/*.java", "src/mateuspires/so/scheduling/*.java"])

# javac -d bin src/mateuspires/so/*.java src/mateuspires/so/scheduling/*.java 