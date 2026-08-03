import numpy as np
import pandas as pd
from sklearn.preprocessing import LabelEncoder
from sklearn.tree import DecisionTreeClassifier, plot_tree, export_graphviz
import matplotlib.pyplot as plt
import graphviz
import os

os.environ["PATH"] += os.pathsep + r"C:\Program Files (x86)\Graphviz\bin"

# Load dataset
df = pd.read_csv("AI/PlayTennis.csv")

# Encode feature columns
for col in ['outlook', 'temp', 'humidity', 'windy']:
    encoder = LabelEncoder()
    df[col] = encoder.fit_transform(df[col])

# Encode target column
target_encoder = LabelEncoder()
df['play'] = target_encoder.fit_transform(df['play'])

# Features and target
X = df[['outlook', 'temp', 'humidity', 'windy']]
y = df['play']

# Train Decision Tree
clf = DecisionTreeClassifier(criterion='entropy', random_state=42)
clf.fit(X, y)

# Plot tree
plt.figure(figsize=(12, 8))
plot_tree(
    clf,
    feature_names=X.columns,
    class_names=target_encoder.classes_,
    filled=True,
    rounded=True
)
plt.show()

# Graphviz visualization
dot_data = export_graphviz(
    clf,
    out_file=None,
    feature_names=X.columns,
    class_names=target_encoder.classes_,
    filled=True,
    rounded=True,
    special_characters=True
)

graph = graphviz.Source(dot_data)
graph.render("playtennis_decision_tree", format="png", cleanup=True)
graph.view()

# Prediction and accuracy
y_pred = clf.predict(X)

print("Predictions:", y_pred)
print("Actual:", y.values)
print("Match:", y_pred == y.values)

accuracy = np.mean(y_pred == y.values)
print(f"Training Accuracy: {accuracy * 100:.1f}%")