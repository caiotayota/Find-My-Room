import { Icon, Item, Label } from 'semantic-ui-react';
import axios from '../../api/axios';
import { useEffect, useState, useRef } from 'react';
import { Ad } from '../../models/Ad';
const AD_URL = '/ads';
import './SearchRoomsStyles.css';

import {
  faSquareParking,
  faPaw,
  faTemperatureArrowUp,
  faBath,
} from '@fortawesome/free-solid-svg-icons';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faMagnifyingGlass } from '@fortawesome/free-solid-svg-icons';

function formatTimestamp(str: string) {
  return 'Posted at: ' + str.substring(0, 10).split('-').reverse().join('-');
}

const regex = /[0-9]+/;

function AdItemList() {
  const [ads, setAds] = useState<Ad[]>([]);
  const [search, setSearch] = useState(false);
  const [query, setQuery] = useState('');

  const adRef = useRef<HTMLDivElement>(null);

  useEffect(() => {
    axios.get(AD_URL).then((response) => {
      console.log(response.data);
      setAds(response.data);
    });
  }, []);

  useEffect(() => {
    function activateSearch() {
      let scrollPosition = window.pageYOffset;
      if (scrollPosition > 200) {
        setSearch(true);
      } else if (scrollPosition < 10) {
        setSearch(false);
      }
    }
    window.addEventListener('scroll', activateSearch);
  });

  return (
    <>
      <div className={`search ${search && 'search-active'}`}>
        <FontAwesomeIcon icon={faMagnifyingGlass} className="search__icon" />
        <input
          className="search__input"
          type="text"
          id="search"
          onChange={(e) => {
            {
              setQuery(e.target.value.toLowerCase());

              if (e.target.value.length > 0) {
                adRef.current?.scrollIntoView();
              } else {
                window.scrollTo(0, 0);
              }
            }
          }}
          placeholder=" Search by location ..."
        />
      </div>
      <div ref={adRef} id="ads"></div>
      <div className="ad-container">
        <Item.Group divided>
          {ads
            .filter(
              (ad) =>
                ad.room.streetAddress.toLowerCase().includes(query) ||
                ad.room.eirCode.toLowerCase().includes(query) ||
                ad.room.roomType.toLowerCase().includes(query)
            )
            .map((ad) => {
              return (
                <>
                  <Item key={'ad_' + ad.id}>
                    <Item.Image
                      label
                      src="https://www.hubbleliving.com/images/rooms/dublin-standard-ensuite.jpg"
                    />

                    <Item.Content>
                      <Item.Header as="a">
                        {ad.room.streetAddress.replace(regex, '')}
                      </Item.Header>
                      <Item.Meta>
                        <span>
                          <Icon name="euro sign" />
                          {ad.rent} / month &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                          {ad.billsIncluded ? (
                            <Label as="a" color="teal" size="small" horizontal>
                              Bills Included
                            </Label>
                          ) : (
                            <></>
                          )}
                        </span>
                      </Item.Meta>
                      <Item.Description>
                        {formatTimestamp(ad.createdAt)}
                      </Item.Description>
                      <Item.Extra>
                        <Label>{ad.room.roomType}</Label>

                        {ad.room.ensuiteBathroom ? (
                          <Label>
                            <FontAwesomeIcon icon={faBath} />
                            &nbsp;&nbsp;Ensuite Bathroom
                          </Label>
                        ) : (
                          <></>
                        )}
                        {ad.owner_occupied ? (
                          <Label>Owner Ocuupied</Label>
                        ) : (
                          <></>
                        )}

                        {ad.parking ? (
                          <Label>
                            <FontAwesomeIcon icon={faSquareParking} />
                            &nbsp;&nbsp;Parking
                          </Label>
                        ) : (
                          <></>
                        )}

                        {ad.petAllowed ? (
                          <Label>
                            <FontAwesomeIcon icon={faPaw} />
                            &nbsp;&nbsp;Pet Allowed
                          </Label>
                        ) : (
                          <></>
                        )}

                        {ad.room.heating ? (
                          <Label>
                            <FontAwesomeIcon icon={faTemperatureArrowUp} />
                            &nbsp;&nbsp;Heating
                          </Label>
                        ) : (
                          <></>
                        )}

                        {ad.room.carpeted ? <Label>Carpeted</Label> : <></>}

                        {ad.dishWasher ? <Label>Dishwasher</Label> : <></>}

                        {ad.dryer ? <Label>Dryer</Label> : <></>}
                      </Item.Extra>
                    </Item.Content>
                  </Item>
                </>
              );
            })}
        </Item.Group>
      </div>
    </>
  );
}

export default AdItemList;
