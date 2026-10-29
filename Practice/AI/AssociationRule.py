import pandas as pd
from mlxtend.preprocessing import TransactionEncoder
from mlxtend.frequent_patterns import apriori, association_rules

data = [
    ['Milk', 'Bread', 'Butter'],
    ['Bread', 'Butter'],
    ['Milk', 'Bread'],
    ['Milk', 'Bread', 'Butter'],
    ['Milk', 'Butter']
]

te = TransactionEncoder()
df = pd.DataFrame(te.fit(data).transform(data), columns=te.columns_)

items = apriori(df, min_support=0.4, use_colnames=True)
print("Frequent Items:\n", items)

rules = association_rules(items, metric='confidence', min_threshold=0.6)
print("Rules:\n", rules[['antecedents', 'consequents', 'confidence']])
