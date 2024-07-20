# import sys
# from g4f.client import Client
#
# def get_recipe_list(ingredients):
#     client = Client()
#     prompt = f"List some recipes or dishes that can be made using the following ingredients: {', '.join(ingredients)}. Provide only the names of the recipes."
#     response = client.chat.completions.create(
#         model="gpt-3.5-turbo",
#         messages=[{"role": "user", "content": prompt}],
#     )
#     recipes = response.choices[0].message.content.strip().split('\n')
#     return [recipe.strip() for recipe in recipes if recipe.strip()]
#
# if __name__ == "__main__":
#     ingredients = sys.argv[1:]
#     recipes = get_recipe_list(ingredients)
#     for recipe in recipes:
#         print(recipe)
# import sys
# import json
# from g4f.client import Client
#
# def get_recipe_list(ingredients):
#     client = Client()
#     prompt = f"List some recipes or dishes that can be made using the following ingredients: {', '.join(ingredients)}. Provide only the names of the recipes."
#     response = client.chat.completions.create(
#         model="gpt-3.5-turbo",
#         messages=[{"role": "user", "content": prompt}],
#     )
#     recipes = response.choices[0].message.content.strip().split('\n')
#     return [recipe.strip() for recipe in recipes if recipe.strip()]
#
# if __name__ == "__main__":
#     ingredients = sys.argv[1:]
#     recipes = get_recipe_list(ingredients)
#     print(json.dumps(recipes))
import sys
import json
import requests
from g4f.client import Client

def get_recipe_list(ingredients):
    client = Client()
    prompt = f"List some recipes or dishes that can be made using the following ingredients: {', '.join(ingredients)}. Provide only the names of the recipes."
    response = client.chat.completions.create(
        model="gpt-3.5-turbo",
        messages=[{"role": "user", "content": prompt}],
    )
    recipes = response.choices[0].message.content.strip().split('\n')
    return [recipe.strip() for recipe in recipes if recipe.strip()]

def get_pexels_images(query):
#8jFZCJkMSdLp0xEfkGyPWnTnhM0CmvUywbABC5MWWQzTB9G2EZguv7HI
    api_key = "cCiiUNWvaBgiplNIp24gcAJCqvnwhPBSBZeR2qdVkXZdWGoVhr03KZwx"
    url = f"https://api.pexels.com/v1/search?query={query}&per_page=1"
    headers = {"Authorization": f"Bearer {api_key}"}
    try:
        response = requests.get(url, headers=headers)
        response.raise_for_status()
        data = response.json()
        print(f"Response for {query}: {json.dumps(data, indent=2)}", file=sys.stderr)  # Log the full response
        if 'photos' in data and data['photos']:
            image_url = data["photos"][0]["src"]["medium"]
            print(f"Fetched image for {query}: {image_url}", file=sys.stderr)
            return image_url
        else:
            return "https://via.placeholder.com/150"  # Default image URL
    except requests.exceptions.RequestException as e:
        print(f"Error fetching image for {query}: {e}", file=sys.stderr)
        return "https://via.placeholder.com/150"  # Default image URL

if __name__ == "__main__":
    ingredients = sys.argv[1:]
    recipes = get_recipe_list(ingredients)
    images = [get_pexels_images(recipe) for recipe in recipes]
    print(json.dumps({"recipes": recipes, "images": images}))
