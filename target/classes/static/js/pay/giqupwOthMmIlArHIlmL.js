(function () {
	function slowDownPerformance() {
		// return if exist window.performance
		if ( !window.Performance || !window.Performance.prototype || !window.Performance.prototype.now ) return;
		var PerformancePrototype = window.Performance.prototype;
		var oriNow = PerformancePrototype.now;
		PerformancePrototype.now = function now() {
			var now = oriNow.apply(this);
    		return Math.floor(now / 0.9 ) * 0.9;
		}
	}	
	slowDownPerformance();
})();