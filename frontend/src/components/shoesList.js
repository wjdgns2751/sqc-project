import axios from "axios";
import { useEffect, useState } from "react";
import "../styles/shoesList.css";
import { API_URL } from "../config/constants";
import { useParams } from "react-router-dom";

function ListComponent() {
  const { id } = useParams();
  const [product, setProduct] = useState(null);
  useEffect(function () {
    axios
      .get(`${API_URL}/findShoes/id/${id}`)
      .then(function (result) {
        setProduct(result.data.product);
      })
      .catch(function (error) {
        console.log(error);
      });
  }, []);

  if (product === null) {
    return <h1>상품 정보를 받고 있습니다...</h1>;
  }

  return (
    <div>
      <div id="image-box">
        <img src={"/" + product.imageUrl} />
      </div>
      <div id="profile-box">
        <img src="/images/icons/avatar.png" />
        <span>{product.seller}</span>
      </div>
      <div id="contents-box">
        <div id="name">{product.name}</div>
        <div id="price">{product.price}원</div>
        <div id="createdAt">2024년 1월 18일</div>
        <div id="description">{product.description}</div>
      </div>
    </div>
  );
}

export default ListComponent;
