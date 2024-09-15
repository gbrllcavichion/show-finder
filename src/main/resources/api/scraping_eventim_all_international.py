from selenium import webdriver
from selenium.webdriver.chrome.service import Service
from selenium.webdriver.common.by import By
from selenium.webdriver.chrome.options import Options
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
from webdriver_manager.chrome import ChromeDriverManager
import json

def fetch_all_international_shows():
    chrome_options = Options()
    chrome_options.add_argument("--headless")

    driver = webdriver.Chrome(service=Service(ChromeDriverManager().install()), options=chrome_options)

    driver.get("https://www.eventim.com.br/events/shows-internacionais-156/")

    try:
        WebDriverWait(driver, 20).until(
            EC.visibility_of_element_located((By.CSS_SELECTOR, "article.listing-item"))
        )
    except Exception as e:
        print("Erro ao carregar os elementos da página.")
        driver.quit()
        return json.dumps([])

    results = []

    events = driver.find_elements(By.CSS_SELECTOR, "article.listing-item")

    for event in events:
        title = event.find_element(By.CSS_SELECTOR, "span.theme-text-color").text
        date = event.find_element(By.CSS_SELECTOR, "div.event-listing-city").text
        results.append({
            "title": title,
            "date": date
        })

    driver.quit()

    return json.dumps(results, ensure_ascii=False)

if __name__ == "__main__":
    print(fetch_all_international_shows())

