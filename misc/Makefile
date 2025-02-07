test-output:
	./basic_stats.py numbers > $@
	cmp $@ expected-output
	-rm -f $@
