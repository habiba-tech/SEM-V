import pandas as pd
import numpy as np
import sklearn
from sklearn import datasets
import matplotlib.pyplot as plt

iris = datasets.load_iris()

df = pd.DataFrame()
df['petal length'] = iris['data'][:, 2]
df['petal width'] = iris['data'][:, 3]
df['target'] = iris['target']

X = df[['petal length', 'petal width']].values
y = df['target'].values

X_train, X_test, y_train, y_test = sklearn.model_selection.train_test_split(
    X, y, test_size=0.2, random_state=42
)

scaler = sklearn.preprocessing.StandardScaler()
X_train_scaled = scaler.fit_transform(X_train)
X_test_scaled = scaler.transform(X_test)

svm_classifier = sklearn.svm.LinearSVC(max_iter=10000)
svm_classifier.fit(X_train_scaled, y_train)

y_predicted = svm_classifier.predict(X_test_scaled)
accuracy = sklearn.metrics.accuracy_score(y_test, y_predicted)
print(f"Model Accuracy: {accuracy:.4f}")

plt.figure(figsize=(8, 6))

x_min, x_max = X_train_scaled[:, 0].min() - 0.5, X_train_scaled[:, 0].max() + 0.5
y_min, y_max = X_train_scaled[:, 1].min() - 0.5, X_train_scaled[:, 1].max() + 0.5
xx, yy = np.meshgrid(np.arange(x_min, x_max, 0.02), np.arange(y_min, y_max, 0.02))

Z = svm_classifier.predict(np.c_[xx.ravel(), yy.ravel()])
Z = Z.reshape(xx.shape)

plt.contourf(xx, yy, Z, alpha=0.3, cmap=plt.cm.coolwarm)

scatter = plt.scatter(X_train_scaled[:, 0], X_train_scaled[:, 1], c=y_train, 
                      cmap=plt.cm.coolwarm, edgecolors='k')

plt.xlabel('Petal Length (Standardized)')
plt.ylabel('Petal Width (Standardized)')
plt.title('LinearSVC Decision Boundaries (Iris Dataset)')

colors = plt.cm.coolwarm(np.linspace(0, 1, 3))
legend_markers = [plt.Line2D([0], [0], marker='o', color='w', markerfacecolor=c, markersize=10, markeredgecolor='k') for c in colors]
plt.legend(legend_markers, iris.target_names, title="Iris Species")

plt.show()
