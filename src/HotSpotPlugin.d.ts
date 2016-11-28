export interface ConnectionInfo {
  /**
   * @property {string}   SSID
   *      The service set identifier (SSID) of the current 802.11 network.
   */
  SSID: string;
  /**
   * @property {string}   BSSID
   *      The basic service set identifier (BSSID) of the current access point.
   */
  BSSID: string;
  /**
   * @property {string}   linkSpeed
   *      The current link speed in Mbps
   */
  linkSpeed: string;
  /**
   * @property {string}   IPAddress
   *      The IP Address
   */
  IPAddress: string;
  /**
   * @property {string}   networkID
   *      Each configured network has a unique small integer ID, used to identify the network when performing operations on the supplicant.
   */
  networkID: string;
}

export interface Network {
  /**
   * @property {string}       SSID
   *      Human readable network name
   */
  SSID: string;
  /**
   * @property {string}       BSSID
   *      MAC Address of the access point
   */
  BSSID: string;
  /**
   * @property {number (int)} frequency
   *      The primary 20 MHz frequency (in MHz) of the channel over which the client is communicating with the access point.
   */
  frequency: number;
  /**
   * @property {number}       level
   *      The detected signal level in dBm, also known as the RSSI.
   */
  level: number;
  /**
   * @property {number}       timestamp
   *      Timestamp in microseconds (since boot) when this result was last seen.
   */
  timestamp: number;
  /**
   * @property {string}       capabilities
   *      Describes the authentication, key management, and encryption schemes supported by the access point.
   */
  capabilities: string;
}
export interface NetworkConfig {
  /**
   * @property {string}   deviceIPAddress - Device IP Address
   */
  deviceIPAddress: string;
  /**
   * @property {string}   deviceMacAddress - Device MAC Address
   */
  deviceMacAddress: string;
  /**
   * @property {string}   gatewayIPAddress - Gateway IP Address
   */
  gatewayIPAddress: string;
  /**
   * @property {string}   gatewayMacAddress - Gateway MAC Address
   */
  gatewayMacAddress: string;
}
export interface HotspotDevice {
  /**
   * @property {string}   ip
   *      Hotspot IP Address
   */
  ip: string;
  /**
   * @property {string}   mac
   *      Hotspot MAC Address
   */
  mac: string;
}
