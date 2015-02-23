(ns io.prismic.render-test
  (:require [clojure.test :refer :all]
            [io.prismic.test-utils :refer :all]
            [io.prismic.api :refer :all]))

(def lbc (get-api "https://lesbonneschoses.prismic.io/api"))
(def public (get-api "https://test-public.prismic.io/api"))
(def micro (get-api "https://micro.prismic.io/api"))
(defn- resolver [link]
  (let [document (-> link :value :document)]
    (str "http://localhost/" (:type document) "/" (:id document))))

(deftest render-fragments

  (testing "render group"
    (let [doc (get-by-id micro "UrDcEAEAAKUbpbND")
          expected (str
                     "<section data-field=\"desc\"><p>Just testing another field in a group section.</p></section>\n"
                     "<section data-field=\"linktodoc\"><a href=\"http://localhost//\">installing-meta-micro</a></section>"
                     "<section data-field=\"linktodoc\"><a href=\"http://localhost//\">using-meta-micro</a></section>")]
      (is (= expected (render (get-fragment doc :docchapter.docs) resolver)))))

;  (testing "render image"
;    (let [doc (get-by-bookmark lbc :stores)
;          html (render/image (get-fragment doc :image))
;          url "https://prismic-io.s3.amazonaws.com/lesbonneschoses/946cdd210d5f341df7f4d8c7ec3d48adbf7a9d65.jpg"]
;      (is (= (str "<img alt=\"\" src=\"" url "\" width=\"1500\" height=\"500\" />") html))))
;
;  (testing "render image view"
;    (let [doc (get-by-id public "Uyr9sgEAAGVHNoFZ")
;          html (render/image-view (get-fragment doc :illustration) :icon)
;          url "https://prismic-io.s3.amazonaws.com/test-public/9f5f4e8a5d95c7259108e9cfdde953b5e60dcbb6.jpg"]
;      (is (= (str "<img alt=\"some alt text\" src=\"" url "\" width=\"100\" height=\"100\" />") html))))
;
;  (testing "render document link"
;    (let [doc (get-by-id lbc "UkL0gMuvzYUANCpi")
;          html (render/document-link (get-fragment doc :location) resolver)
;          url "http://localhost/store/UkL0gMuvzYUANCpW"]
;      (is (= (str "<a href=\"" url "\">new-york-fifth-avenue</a>") html))))
;
;  (testing "render web link"
;    (let [doc (get-by-id public "Uy4VGQEAAPQzRDR9")
;          html (render/web-link (get-fragment doc :related))
;          url "https://github.com/prismicio"]
;      (is (= (str "<a href=\"" url "\">" url "</a>") html))))
;
;  (testing "render file link"
;    (let [doc (get-by-id public "Uy4VGQEAAPQzRDR9")
;          html (render/file-link (get-fragment doc :download))
;          url "https://prismic-io.s3.amazonaws.com/test-public%2Feb14f588-07b4-4df7-be43-5b6f6383d202_ambiance-radio.m3u"]
;      (is (= (str "<a href=\"" url "\">ambiance-radio.m3u</a>") html))))
;
;  (testing "render number"
;    (let [doc (get-by-id lbc "UkL0gMuvzYUANCpT")
;          html (render/number (get-fragment doc :price))]
;      (is (= "<span class=\"number\">3.0</span>" html))))
;
;  (testing "render color"
;    (let [doc (get-by-id lbc "UkL0gMuvzYUANCpT")
;          html (render/color (get-fragment doc :color))]
;      (is (= "<span class=\"color\">#f9001b</span>" html))))
;
;  (testing "render date"
;    (let [fragment (json-mock "date_fragment.json")
;          html (render/date fragment)]
;      (is (= "<span class=\"date\">2013/08/17</span>" html))))
;
;  (testing "render date with custom pattern"
;    (let [fragment (json-mock "date_fragment.json")
;          html (render/date fragment "dd.MM.yyyy")]
;      (is (= "<span class=\"date\">17.08.2013</span>" html))))
;
;  (testing "render text"
;    (let [doc (get-by-id lbc "UkL0gMuvzYUANCpn")
;          html (render/text (get-fragment doc :author))]
;      (is (= "<span class=\"text\">Tsutomu Kabayashi, Pastry Dresser</span>" html))))
;
;  (testing "render document"
;    (let [doc (json-mock "document.json")
;          expected "<p>Initially started in Paris in 1992, we are now present in <strong>Paris, London, Tokyo and New York</strong>, so you may be lucky with a <em>Les Bonnes Choses</em> shop in your town. We always welcome in our shops the most interested to discover new pastry sensations, and we thrive as we advise you towards your next taste adventures.</p>\n\n<p>If you'd like to challenge us, learn that we like to be challenged! You can place a special order, defining roughly what tastes you like, and how you would like your order to make you feel, and we take it from there!</p>"]
;      (is (= (render/document doc resolver) expected))))
;
;  (testing "render select"
;    (let [html (render/select (json-mock "select_fragment.json"))]
;      (is (= "<span class=\"text\">&amp;my &lt;value&gt; #abcde</span>" html))))
;
;  (testing "render embed"
;    (let [doc (get-by-bookmark public :links)
;          html (render/embed (get-fragment doc :embed))]
;     (is (= "<div data-oembed=\"https://gist.github.com/srenault/71b4f1e62783c158f8af\" data-oembed-type=\"rich\" data-oembed-provider=\"github\"><script src=\"https://gist.github.com/srenault/71b4f1e62783c158f8af.js\"></script></div>" html))))
;
    )
