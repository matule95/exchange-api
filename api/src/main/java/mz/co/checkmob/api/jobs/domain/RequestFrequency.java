package mz.co.checkmob.api.jobs.domain;

public enum RequestFrequency {
    DAILY {
        @Override
        public Long getMinutes() {
            return 24*60L;
        }
    },
    WEEKLY{
        @Override
        public Long getMinutes() {
            return 7*24*60L;
        }
    },
    MONTHLY{
        @Override
        public Long getMinutes() {
            return 30*24*60L;
        }
    },QUARTERLY{
        @Override
        public Long getMinutes() {
            return 3*30*24*60L;
        }
    },SEMIANNUALLY{
        @Override
        public Long getMinutes() {
            return 6*30*24*60L;
        }
    }, ANNUALLY {
        @Override
        public Long getMinutes() {
            return 365*24*60L;
        }
    };

    public abstract Long getMinutes();
    }
