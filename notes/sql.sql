SELECT farmers.id, farmer_classes.name, farmer_tweets.tweet_text, farmer_tweets.likes, farmer_tweets.retweets FROM farmers
INNER JOIN farmer_classes ON farmers.id = farmer_classes.farmer_id
INNER JOIN farmer_tweets ON farmer_classes.id = farmer_tweets.class_id
WHERE farmers.id IN 
(SELECT id FROM farmers WHERE name = 'Hans234226545334');