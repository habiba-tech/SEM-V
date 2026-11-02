from sklearn import datasets
from sklearn.model_selection import train_test_split
from sklearn.metrics import accuracy_score
from sklearn.naive_bayes import GaussianNB

iris = datasets.load_iris()
x = iris.data
y = iris.target

x_train, x_test, y_train, y_test = train_test_split(
    x, y, test_size=0.21, random_state=44
)

model = GaussianNB()
model.fit(x_train, y_train)

predictions = model.predict(x_test)
probabilities = model.predict_proba(x_test[:1])
accuracy = accuracy_score(y_test, predictions) * 100

print(f"Accuracy : {accuracy}%")
print(f"Probabilities : {probabilities}")
