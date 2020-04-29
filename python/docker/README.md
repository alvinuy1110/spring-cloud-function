
Reference
=========

* https://github.com/apache/openwhisk/blob/master/docs/actions-python.md

WSK Commands
============

wsk -i action create helloPython hello.py
wsk -i action invoke --result helloPython --param name World
wsk -i action delete helloPython

wsk -i activation get <activationId>



