import helper

# if __name__ == '__main__':
#     # execute only if run as the entry point into the program
#     return helper.hello()


def main(args):
    name = args.get("name", "stranger")
    return helper.hello(name)
