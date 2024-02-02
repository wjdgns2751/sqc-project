import React from "react";
import "../styles/shoesMain.css";
import axios from "axios";
import { Link } from "react-router-dom";
import dayjs from "dayjs";
import relativeTime from "dayjs/plugin/relativeTime";
import { API_URL } from "../config/constants";

dayjs.extend(relativeTime);
dayjs.locale("ko");

function MainComponent() {
  const [products, setProducts] = React.useState([]);
  React.useEffect(
    function () {
      axios
        .get(`${API_URL}/findShoesList`, {
          params: {
            paramKey: paramValue, // 요구사항 값을 파라미터로 전달
          },
        })
        .then(function (result) {
          const products = result.data.products;
          setProducts(products);
        })
        .catch(function (error) {
          console.error("에러 발생 : ", error);
        });
    },
    [paramValue]
  ); // paramValue가 변경될 때마다 useEffect가 실행되도록 설정

  return (
    <div>
      <div id="banner">
        <img src="images/banners/banner3.png" />
      </div>
      <h1>판매되는상품들</h1>
      <div id="product-list">
        {products.map(function (product, index) {
          return (
            <div className="product-card">
              <Link className="product-link" to={`/products/${product.id}`}>
                <div>
                  <img className="product-img" src={product.imageUrl} />
                </div>
                <div className="product-contents">
                  <span className="product-name">{product.name}</span>
                  <span className="product-price">{product.price}원</span>
                  <div className="product-footer">
                    <div className="product-seller">
                      <img
                        className="product-avatar"
                        src="images/icons/avatar.png"
                      />
                      <span>{product.seller}</span>
                    </div>
                    <span className="product-date">
                      {dayjs(product.createdAt).fromNow()}
                    </span>
                  </div>
                </div>
              </Link>
            </div>
          );
        })}
      </div>
    </div>
  );
}

export default MainComponent;
