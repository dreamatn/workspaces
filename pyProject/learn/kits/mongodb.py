import pymongo

myclient = pymongo.MongoClient('mongodb://localhost:27017/')
mydb = myclient['runoobdb']
mycol = mydb["sites"]

mydict = {"name": "Google", "alexa": "1", "url": "https://www.google.com"}

x = mycol.insert_one(mydict)

print(x.inserted_id)
