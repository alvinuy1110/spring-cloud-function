import pyjokes

def main(args):
    return {"joke": pyjokes.get_joke(), "main": "true"}

def joke(params):
    return {"joke": pyjokes.get_joke()}
