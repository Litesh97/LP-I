import os
import aiml

message = ""

kernel = aiml.Kernel()
if os.path.isfile("bot_brain.brn"):
    kernel.bootstrap(brainFile="bot_brain.brn")
else:
    kernel.bootstrap(learnFiles="std-startup.xml", commands="load aiml b")
    kernel.saveBrain("bot_brain.brn")

try:
    while True:
        message = input(">> ")
        if message == "save":
            kernel.saveBrain("bot_brain.brn")
        elif message == "exit":
            break
        else:
            response = kernel.respond(message)
            print(response)
            print()
except Exception as e:
    print(e)
