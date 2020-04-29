This demonstrates using python ZIP mode.

* NOT YET WORKING for imported 3rd party module.
* Works for custom module

```
 "The action failed to generate or locate a binary. See logs for details."

 no module found
```

* Must return a dictionary



Reference
=========

* https://github.com/apache/openwhisk/blob/master/docs/actions-python.md
* http://jamesthom.as/blog/2017/04/27/python-packages-in-openwhisk/
* https://www.serverless.com/open-source/
    (generic framework for serverless)

Python setup
============

sudo apt install python3
sudo apt install pip3
sudo apt install python3-venv

python -m venv <env_name>
source <env_name>/bin/activate

ZIP Command
===========

zip -r helloPython.zip __main__.py helper.py

# works but the package is BIG
zip -r jokes.zip myenv __main__.py

# make the packaging small
zip -r jokes_small.zip myenv/bin/activate myenv/lib/python3.6/site-packages/pyjokes __main__.py

WSK Commands
============

wsk -i action create helloPython hello.py
wsk -i action invoke --result helloPython --param name World
wsk -i action delete helloPython

wsk -i activation get <activationId>

## This works
wsk -i action create helloPython2 --kind python:3 helloPython.zip
wsk -i action invoke --result helloPython2 --param name Joe
wsk -i action delete helloPython2


## not yet working
wsk -i action create jokes --kind python:3 --main joke jokes_small.zip
wsk -i action delete jokes 
wsk -i action invoke jokes --blocking --result
wsk -i activation get <activationId>

