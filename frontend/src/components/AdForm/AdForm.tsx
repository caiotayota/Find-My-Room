import { useEffect, useState, useRef } from 'react';
import regions from '../../models/regions.json';
import roomCategories from '../../models/roomCategories.json';
import axios from '../../api/axios';
import { AxiosError } from 'axios';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faMagnifyingGlass, faUpload } from '@fortawesome/free-solid-svg-icons';

import './AdFormStyles.css';

const POST_AD_URL = '/ads/new';
const IMAGE_UPLOAD_URL = '/img/upload';

function AdForm({ open, onClose }: any) {
  if (!open) return null;

  const userRef = useRef<HTMLInputElement>(null);
  const errorRef = useRef<HTMLParagraphElement>(null);

  const [streetAddress, setStreetAddress] = useState('');
  const [eirCode, setEirCode] = useState('');
  const [roomType, setRoomType] = useState('');
  const [rent, setRent] = useState('');
  const [billsIncluded, setBillsIncluded] = useState<boolean>(false);
  const [ownerOccupied, setOwnerOccupied] = useState<boolean>(false);
  const [ensuiteBathroom, setEnsuiteBathroom] = useState<boolean>(false);
  const [parking, setParking] = useState<boolean>(false);
  const [petAllowed, setPetAllowed] = useState<boolean>(false);
  const [heating, setHeating] = useState<boolean>(false);
  const [carpeted, setCarpeted] = useState<boolean>(false);
  const [washingMachine, setWashingMachine] = useState<boolean>(false);
  const [dryer, setDryer] = useState<boolean>(false);
  const [dishWasher, setDishWasher] = useState<boolean>(false);

  const [roomImage, setRoomImage] = useState('');
  const [imgUploaded, setImgUploaded] = useState<boolean>(false);
  const [roomImageId, setRoomImageId] = useState<number>();

  const [errorMsg, setErrorMsg] = useState('');
  const [success, setSuccess] = useState(false);

  useEffect(() => {
    function activateLogo() {
      let scrollPosition = window.pageYOffset;
      if (scrollPosition < 200) {
        onClose();
      }
    }
    window.addEventListener('scroll', activateLogo);
  });

  function timeout(delay: number) {
    return new Promise((res) => setTimeout(res, delay));
  }

  function handleImage(e: any) {
    setRoomImage(e.target.files[0]);
  }
  const handleApi = async (e: { preventDefault: () => void }) => {
    e.preventDefault();
    const formData = new FormData();
    formData.append('image', roomImage);

    try {
      const imgResponse = await axios
        .post(IMAGE_UPLOAD_URL, formData)
        .then((res) => setRoomImageId(res.data));
      setImgUploaded(true);
    } catch (err: unknown) {
      const error = err as AxiosError;

      if (error?.response?.status === 409) {
        setErrorMsg('Image e');
        console.log('image er');
      } else {
        setErrorMsg('Upload Failed');
        console.log('upload failed');
      }
      errorRef.current?.focus();
    }
  };

  const handleSubmit = async (e: { preventDefault: () => void }) => {
    e.preventDefault();

    handleApi;

    try {
      const response = await axios.post(
        POST_AD_URL,

        JSON.stringify({
          streetAddress,
          eirCode,
          roomType,
          ensuiteBathroom,
          heating,
          carpeted,
          rent,
          billsIncluded,
          parking,
          ownerOccupied,
          petAllowed,
          washingMachine,
          dryer,
          dishWasher,
          roomImageId,
        }),
        {
          headers: {
            'Content-Type': 'application/json',
            Authorization:
              '' +
              localStorage.getItem('tokenType') +
              localStorage.getItem('accessToken'),
          },
        }
      );

      setSuccess(true);
    } catch (err: unknown) {
      const error = err as AxiosError;

      if (error?.response?.status === 409) {
        setErrorMsg('Email is already registred');
        console.log('taken');
      } else {
        setErrorMsg('Registration Failed');
        console.log('failed');
      }
      errorRef.current?.focus();
    }
  };

  return success ? (
    <>
      <div className="overlay2">
        <div className="modalContainer">
          <div className="modalRight">
            <p
              onClick={() => {
                onClose();
                window.scrollTo(0, 0);
                window.location.reload();
              }}
              className="closeBtn"
            >
              X
            </p>
            <div className="success">
              <h1>Your room was posted succefully!</h1>
              <p className="findMyRoomLink">
                <a
                  href="/"
                  onClick={() => {
                    onClose;
                  }}
                >
                  <FontAwesomeIcon
                    icon={faMagnifyingGlass}
                    className="search__icon"
                  />
                  Find my room
                </a>
              </p>
            </div>
          </div>
        </div>
      </div>
    </>
  ) : (
    <>
      <div className="overlay2">
        <div className="modalContainer">
          <div className="modalRight">
            <p
              onClick={() => {
                onClose();
                window.scrollTo(0, 0);
              }}
              className="closeBtn"
            >
              X
            </p>
            <div>
              <div className={errorMsg ? 'errorMsg' : 'offscreen'}>
                <p ref={errorRef} aria-live="assertive">
                  {errorMsg}
                </p>
              </div>
              <h1>Post a room</h1>
              <form className="adForm" onSubmit={handleSubmit}>
                <label htmlFor="streetAddress">Street Address:</label>
                <input
                  className="inputField"
                  type="text"
                  id="streetAdress"
                  ref={userRef}
                  onChange={(e) => setStreetAddress(e.target.value)}
                  required
                />

                <label htmlFor="eirCode">EirCode:</label>
                <input
                  type="text"
                  id="eirCode"
                  placeholder="e.g. A96-E771 ..."
                  ref={userRef}
                  onChange={(e) => setEirCode(e.target.value)}
                  required
                />

                <label htmlFor="region">
                  Region/Area:
                  <select
                    id="region"
                    className="adForm"
                    onChange={(e) =>
                      setStreetAddress(streetAddress + ', ' + e.target.value)
                    }
                  >
                    <option value="0"></option>
                    {regions.map((region) => (
                      <option key={region.code} value={region.region}>
                        {region.region}
                      </option>
                    ))}
                  </select>
                </label>

                <label htmlFor="roomCategory">
                  Room Category:
                  <br />
                  <select
                    id="roomCategory"
                    className="adForm"
                    onChange={(e) => setRoomType(e.target.value)}
                  >
                    <option value="0"></option>
                    {roomCategories.map((category) => (
                      <option key={category.roomType} value={category.roomType}>
                        {category.roomType}
                      </option>
                    ))}
                  </select>
                </label>

                <label htmlFor="rent" className="rent">
                  Rent Price: <br />
                  <span id="span">€</span>
                  <input
                    id="rent"
                    type="number"
                    min="100.0"
                    step="25.00"
                    max="5000"
                    placeholder="700.00"
                    ref={userRef}
                    onChange={(e) => setRent(e.target.value)}
                    required
                  />
                </label>
                <hr />

                <div className="checkbox">
                  <label htmlFor="billsIncluded">
                    <input
                      id="billsIncluded"
                      type="checkbox"
                      defaultChecked={billsIncluded}
                      onChange={() => {
                        setBillsIncluded(!billsIncluded);
                      }}
                    />
                    &nbsp; Bills Included
                  </label>
                  <label className="labelCheckbox">
                    <input
                      type="checkbox"
                      defaultChecked={ownerOccupied}
                      onChange={() => setOwnerOccupied(!ownerOccupied)}
                    />
                    &nbsp; Owner Occupied
                  </label>

                  <label className="labelCheckbox">
                    <input
                      type="checkbox"
                      defaultChecked={ensuiteBathroom}
                      onChange={() => setEnsuiteBathroom(!ensuiteBathroom)}
                    />
                    &nbsp; Ensuite Bathroom
                  </label>
                  <label className="labelCheckbox">
                    <input
                      type="checkbox"
                      defaultChecked={parking}
                      onChange={() => setParking(!parking)}
                    />
                    &nbsp; Car Parking
                  </label>
                  <label className="labelCheckbox">
                    <input
                      type="checkbox"
                      defaultChecked={petAllowed}
                      onChange={() => setPetAllowed(!petAllowed)}
                    />
                    &nbsp; Pet Allowed
                  </label>
                  <label className="labelCheckbox">
                    <input
                      type="checkbox"
                      defaultChecked={heating}
                      onChange={() => setHeating(!heating)}
                    />
                    &nbsp; Heating
                  </label>
                  <label className="labelCheckbox">
                    <input
                      type="checkbox"
                      defaultChecked={carpeted}
                      onChange={() => setCarpeted(!carpeted)}
                    />
                    &nbsp; Carpeted
                  </label>
                  <label className="labelCheckbox">
                    <input
                      type="checkbox"
                      defaultChecked={washingMachine}
                      onChange={() => setWashingMachine(!setWashingMachine)}
                    />
                    &nbsp; Washing Machine
                  </label>
                  <label className="labelCheckbox">
                    <input
                      type="checkbox"
                      defaultChecked={dryer}
                      onChange={() => setDryer(!setDryer)}
                    />
                    &nbsp; Dryer
                  </label>
                  <label className="labelCheckbox">
                    <input
                      type="checkbox"
                      defaultChecked={dishWasher}
                      onChange={() => setDishWasher(!setDishWasher)}
                    />
                    &nbsp; Dishwasher
                  </label>
                </div>
                <hr />
                <label htmlFor="roomImage">Select a room photo:</label>
                <input
                  className="roomInput"
                  type="file"
                  ref={userRef}
                  onChange={handleImage}
                  required
                ></input>
                <span className="uploadImage">
                  <button
                    className="uploadImage"
                    onClick={handleApi}
                    onBlurCapture={() => {
                      setErrorMsg('');
                    }}
                  >
                    <FontAwesomeIcon icon={faUpload} className="uploadIcon" />
                    Upload Image
                  </button>
                  {imgUploaded ? (
                    <div className="successMsg">
                      <p>The image was uploaded succefully</p>
                    </div>
                  ) : (
                    <div className="failMsg">
                      <p> Please, upload a room photo!</p>
                    </div>
                  )}
                </span>

                <button
                  className="submit"
                  disabled={!imgUploaded ? true : false}
                >
                  Submit
                </button>
              </form>
            </div>
          </div>
        </div>
      </div>
    </>
  );
}

export default AdForm;
