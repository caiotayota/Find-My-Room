function formatTimestamp(str: string) {
    return 'Posted at: ' + str.substring(0, 10).split('-').reverse().join('-');
  }

  export default formatTimestamp;