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
  // const [products, setProducts] = React.useState([]);
  const [sqcDocuments, setSqcDocuments] = React.useState([]);
  React.useEffect(function () {
    axios
      .get(`${API_URL}/findShoesList`)
      .then(function (result) {
        // const products = result.data.products;
        // setProducts(products);

        const sqcDocumentList = result.data.map(item => item.sqcDocument);
        setSqcDocuments(sqcDocumentList);


        console.log(sqcDocumentList[0].id);


      })
      .catch(function (error) {
        console.error("에러 발생 : ", error);
      });
  }, []);

  return (

    <div>
      <div id="banner">
        <img src="images/banners/banner3.png" />
      </div>
      <h1>판매되는상품들</h1>
      <div id="product-list">
        {sqcDocuments.map(function (sqcDocument, index) {

          return (
            <div className="product-card" key={index}>
              <Link className="product-link" to={`/products/${sqcDocument.id}`}>
                <div>
                  <img className="product-img" src={sqcDocument.img} />
                </div>
                <div className="product-contents">
                  <span className="product-name">{sqcDocument.name}</span>
                  <span className="product-price">{sqcDocument.price}원</span>
                  {/* 나머지 속성들도 필요한 대로 렌더링 */}
                </div>
              </Link>
            </div>
          );
        })}
      </div>
    </div>


    // <div>
    //   <div id="banner">
    //     <img src="images/banners/banner3.png" />
    //   </div>
    //   <h1>판매되는상품들</h1>
    //   <div id="product-list">
    //     {products.map(function (product, index) {
    //       return (
    //         <div className="product-card">
    //           <Link className="product-link" to={`/products/${product.id}`}>
    //             <div>
    //               <img className="product-img" src={product.imageUrl} />
    //             </div>
    //             <div className="product-contents">
    //               <span className="product-name">{product.name}</span>
    //               <span className="product-price">{product.price}원</span>
    //               <div className="product-footer">
    //                 <div className="product-seller">
    //                   <img
    //                     className="product-avatar"
    //                     src="images/icons/avatar.png"
    //                   />
    //                   <span>{product.seller}</span>
    //                 </div>
    //                 <span className="product-date">
    //                   {dayjs(product.createdAt).fromNow()}
    //                 </span>
    //               </div>
    //             </div>
    //           </Link>
    //         </div>
    //       );
    //     })}
    //   </div>
    // </div>
  );
}

export default MainComponent;
