This demonstrates using python ZIP mode.

* WORKING for imported 3rd party module.
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
sudo apt install virtualenv
sudo pip3 install virtualenv

# Step 4: Launch your Python 3 virtual environment, here the name of my virtual environment will be env3
virtualenv -p python3 virtualenv


source virtualenv/bin/activate
deactivate

## module specific
pip install pyjokes
create file __main__.py

python -i .
>> joke({})

Docker
======
```
 docker run --rm -v "$PWD:/tmp" openwhisk/python3action sh \
  -c "cd tmp; virtualenv virtualenv; source virtualenv/bin/activate; pip install pyjokes;"
```  
  
ZIP Command
===========


# works but the package is BIG
zip -r jokes.zip myenv __main__.py

# make the packaging small
zip -r jokes_small.zip virtualenv/bin/activate_this.py virtualenv/lib/python3.6/site-packages/pyjokes __main__.py

WSK Commands
============


## This works
wsk -i action create jokes --kind python:3 --main joke jokes_small.zip --web true
wsk -i action delete jokes 
wsk -i action invoke jokes --blocking --result
wsk -i activation get <activationId>

wsk -i action get jokes --url


WEB URL
=======

https://172.17.0.4:31001/api/v1/web/guest/default/jokes.json

