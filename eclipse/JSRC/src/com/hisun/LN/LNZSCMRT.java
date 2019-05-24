package com.hisun.LN;

import com.hisun.SC.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class LNZSCMRT {
    BigDecimal bigD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String CPN_SCSSCKDT = "SCSSCKDT";
    String WS_ERR_MSG = " ";
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCCKDT SCCCKDT = new SCCCKDT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCGWA SCCGWA;
    LNCSCMRT LNCSCMRT;
    public void MP(SCCGWA SCCGWA, LNCSCMRT LNCSCMRT) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCSCMRT = LNCSCMRT;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZSCMRT return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B020_MAIN_PROC();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNCSCMRT.COMP_MTH);
        if (LNCSCMRT.COMP_MTH == ' ' 
            || (LNCSCMRT.COMP_MTH != 'N' 
            && LNCSCMRT.COMP_MTH != 'H' 
            && LNCSCMRT.COMP_MTH != 'L' 
            && LNCSCMRT.COMP_MTH != 'V')) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_MUST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, LNCSCMRT.RATE1);
        CEP.TRC(SCCGWA, LNCSCMRT.RATE2);
        CEP.TRC(SCCGWA, LNCSCMRT.RATE3);
        if (LNCSCMRT.RATE1 == 0 
            && LNCSCMRT.RATE2 == 0 
            && LNCSCMRT.RATE3 == 0) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_MUST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B020_MAIN_PROC() throws IOException,SQLException,Exception {
        if (LNCSCMRT.COMP_MTH == 'N') {
            if (LNCSCMRT.RATE1 != 0) {
                LNCSCMRT.ALL_RATE = LNCSCMRT.RATE1;
            } else {
                if (LNCSCMRT.RATE2 != 0) {
                    LNCSCMRT.ALL_RATE = LNCSCMRT.RATE2;
                } else {
                    LNCSCMRT.ALL_RATE = LNCSCMRT.RATE3;
                }
            }
        } else if (LNCSCMRT.COMP_MTH == 'V') {
            if (LNCSCMRT.RATE1 != 0 
                && LNCSCMRT.RATE2 != 0 
                && LNCSCMRT.RATE3 != 0) {
                LNCSCMRT.ALL_RATE = ( LNCSCMRT.RATE1 + LNCSCMRT.RATE2 + LNCSCMRT.RATE3 ) / 3;
                bigD = new BigDecimal(LNCSCMRT.ALL_RATE);
                LNCSCMRT.ALL_RATE = bigD.setScale(8, RoundingMode.HALF_UP).doubleValue();
            }
            if (LNCSCMRT.RATE1 != 0 
                && LNCSCMRT.RATE2 == 0 
                && LNCSCMRT.RATE3 != 0) {
                LNCSCMRT.ALL_RATE = ( LNCSCMRT.RATE1 + LNCSCMRT.RATE3 ) / 2;
                bigD = new BigDecimal(LNCSCMRT.ALL_RATE);
                LNCSCMRT.ALL_RATE = bigD.setScale(8, RoundingMode.HALF_UP).doubleValue();
            }
            if (LNCSCMRT.RATE1 == 0 
                && LNCSCMRT.RATE2 != 0 
                && LNCSCMRT.RATE3 != 0) {
                LNCSCMRT.ALL_RATE = ( LNCSCMRT.RATE2 + LNCSCMRT.RATE3 ) / 2;
                bigD = new BigDecimal(LNCSCMRT.ALL_RATE);
                LNCSCMRT.ALL_RATE = bigD.setScale(8, RoundingMode.HALF_UP).doubleValue();
            }
            if (LNCSCMRT.RATE1 != 0 
                && LNCSCMRT.RATE2 != 0 
                && LNCSCMRT.RATE3 == 0) {
                LNCSCMRT.ALL_RATE = ( LNCSCMRT.RATE1 + LNCSCMRT.RATE2 ) / 2;
                bigD = new BigDecimal(LNCSCMRT.ALL_RATE);
                LNCSCMRT.ALL_RATE = bigD.setScale(8, RoundingMode.HALF_UP).doubleValue();
            }
            if (LNCSCMRT.RATE1 != 0 
                && LNCSCMRT.RATE2 == 0 
                && LNCSCMRT.RATE3 == 0) {
                LNCSCMRT.ALL_RATE = LNCSCMRT.RATE1;
            }
            if (LNCSCMRT.RATE1 == 0 
                && LNCSCMRT.RATE2 != 0 
                && LNCSCMRT.RATE3 == 0) {
                LNCSCMRT.ALL_RATE = LNCSCMRT.RATE2;
            }
            if (LNCSCMRT.RATE1 == 0 
                && LNCSCMRT.RATE2 == 0 
                && LNCSCMRT.RATE3 != 0) {
                LNCSCMRT.ALL_RATE = LNCSCMRT.RATE3;
            }
        } else if (LNCSCMRT.COMP_MTH == 'H') {
            if (LNCSCMRT.RATE1 > LNCSCMRT.RATE2 
                && LNCSCMRT.RATE1 > LNCSCMRT.RATE3) {
                LNCSCMRT.ALL_RATE = LNCSCMRT.RATE1;
            }
            if (LNCSCMRT.RATE1 == LNCSCMRT.RATE2 
                && LNCSCMRT.RATE1 > LNCSCMRT.RATE3) {
                LNCSCMRT.ALL_RATE = LNCSCMRT.RATE1;
            }
            if (LNCSCMRT.RATE2 > LNCSCMRT.RATE1 
                && LNCSCMRT.RATE2 > LNCSCMRT.RATE3) {
                LNCSCMRT.ALL_RATE = LNCSCMRT.RATE2;
            }
            if (LNCSCMRT.RATE2 == LNCSCMRT.RATE1 
                && LNCSCMRT.RATE2 > LNCSCMRT.RATE3) {
                LNCSCMRT.ALL_RATE = LNCSCMRT.RATE2;
            }
            if (LNCSCMRT.RATE3 > LNCSCMRT.RATE1 
                && LNCSCMRT.RATE3 > LNCSCMRT.RATE2) {
                LNCSCMRT.ALL_RATE = LNCSCMRT.RATE3;
            }
            if (LNCSCMRT.RATE3 == LNCSCMRT.RATE1 
                && LNCSCMRT.RATE3 > LNCSCMRT.RATE2) {
                LNCSCMRT.ALL_RATE = LNCSCMRT.RATE3;
            }
            if (LNCSCMRT.RATE3 == LNCSCMRT.RATE1 
                && LNCSCMRT.RATE3 == LNCSCMRT.RATE2) {
                LNCSCMRT.ALL_RATE = LNCSCMRT.RATE3;
            }
            if (LNCSCMRT.RATE1 != 0 
                && LNCSCMRT.RATE2 == 0 
                && LNCSCMRT.RATE3 == 0) {
                LNCSCMRT.ALL_RATE = LNCSCMRT.RATE1;
            }
            if (LNCSCMRT.RATE1 == 0 
                && LNCSCMRT.RATE2 != 0 
                && LNCSCMRT.RATE3 == 0) {
                LNCSCMRT.ALL_RATE = LNCSCMRT.RATE2;
            }
            if (LNCSCMRT.RATE1 == 0 
                && LNCSCMRT.RATE2 == 0 
                && LNCSCMRT.RATE3 != 0) {
                LNCSCMRT.ALL_RATE = LNCSCMRT.RATE3;
            }
        } else if (LNCSCMRT.COMP_MTH == 'L') {
            if (LNCSCMRT.RATE1 < LNCSCMRT.RATE2 
                && LNCSCMRT.RATE1 < LNCSCMRT.RATE3 
                && LNCSCMRT.RATE1 != 0 
                && LNCSCMRT.RATE2 != 0 
                && LNCSCMRT.RATE3 != 0) {
                LNCSCMRT.ALL_RATE = LNCSCMRT.RATE1;
            }
            if (LNCSCMRT.RATE1 == LNCSCMRT.RATE2 
                && LNCSCMRT.RATE1 < LNCSCMRT.RATE3 
                && LNCSCMRT.RATE1 != 0 
                && LNCSCMRT.RATE2 != 0 
                && LNCSCMRT.RATE3 != 0) {
                LNCSCMRT.ALL_RATE = LNCSCMRT.RATE1;
            }
            if (LNCSCMRT.RATE2 < LNCSCMRT.RATE1 
                && LNCSCMRT.RATE2 < LNCSCMRT.RATE3 
                && LNCSCMRT.RATE1 != 0 
                && LNCSCMRT.RATE2 != 0 
                && LNCSCMRT.RATE3 != 0) {
                LNCSCMRT.ALL_RATE = LNCSCMRT.RATE2;
            }
            if (LNCSCMRT.RATE2 == LNCSCMRT.RATE1 
                && LNCSCMRT.RATE2 < LNCSCMRT.RATE3 
                && LNCSCMRT.RATE1 != 0 
                && LNCSCMRT.RATE2 != 0 
                && LNCSCMRT.RATE3 != 0) {
                LNCSCMRT.ALL_RATE = LNCSCMRT.RATE2;
            }
            if (LNCSCMRT.RATE3 < LNCSCMRT.RATE1 
                && LNCSCMRT.RATE3 < LNCSCMRT.RATE2 
                && LNCSCMRT.RATE1 != 0 
                && LNCSCMRT.RATE2 != 0 
                && LNCSCMRT.RATE3 != 0) {
                LNCSCMRT.ALL_RATE = LNCSCMRT.RATE3;
            }
            if (LNCSCMRT.RATE3 == LNCSCMRT.RATE1 
                && LNCSCMRT.RATE3 < LNCSCMRT.RATE2 
                && LNCSCMRT.RATE1 != 0 
                && LNCSCMRT.RATE2 != 0 
                && LNCSCMRT.RATE3 != 0) {
                LNCSCMRT.ALL_RATE = LNCSCMRT.RATE3;
            }
            if (LNCSCMRT.RATE3 == LNCSCMRT.RATE1 
                && LNCSCMRT.RATE3 == LNCSCMRT.RATE2 
                && LNCSCMRT.RATE1 != 0 
                && LNCSCMRT.RATE2 != 0 
                && LNCSCMRT.RATE3 != 0) {
                LNCSCMRT.ALL_RATE = LNCSCMRT.RATE3;
            }
            if (LNCSCMRT.RATE3 < LNCSCMRT.RATE2 
                && LNCSCMRT.RATE1 == 0 
                && LNCSCMRT.RATE2 != 0 
                && LNCSCMRT.RATE3 != 0) {
                LNCSCMRT.ALL_RATE = LNCSCMRT.RATE3;
            } else {
                if (LNCSCMRT.RATE1 == 0 
                    && LNCSCMRT.RATE2 != 0 
                    && LNCSCMRT.RATE3 != 0) {
                    LNCSCMRT.ALL_RATE = LNCSCMRT.RATE2;
                }
            }
            if (LNCSCMRT.RATE3 == LNCSCMRT.RATE2 
                && LNCSCMRT.RATE1 == 0 
                && LNCSCMRT.RATE2 != 0 
                && LNCSCMRT.RATE3 != 0) {
                LNCSCMRT.ALL_RATE = LNCSCMRT.RATE3;
            }
            if (LNCSCMRT.RATE3 < LNCSCMRT.RATE1 
                && LNCSCMRT.RATE1 != 0 
                && LNCSCMRT.RATE2 == 0 
                && LNCSCMRT.RATE3 != 0) {
                LNCSCMRT.ALL_RATE = LNCSCMRT.RATE3;
            } else {
                if (LNCSCMRT.RATE1 != 0 
                    && LNCSCMRT.RATE2 == 0 
                    && LNCSCMRT.RATE3 != 0) {
                    LNCSCMRT.ALL_RATE = LNCSCMRT.RATE1;
                }
            }
            if (LNCSCMRT.RATE3 == LNCSCMRT.RATE1 
                && LNCSCMRT.RATE1 != 0 
                && LNCSCMRT.RATE2 == 0 
                && LNCSCMRT.RATE3 != 0) {
                LNCSCMRT.ALL_RATE = LNCSCMRT.RATE3;
            }
            if (LNCSCMRT.RATE1 < LNCSCMRT.RATE2 
                && LNCSCMRT.RATE1 != 0 
                && LNCSCMRT.RATE2 != 0 
                && LNCSCMRT.RATE3 == 0) {
                LNCSCMRT.ALL_RATE = LNCSCMRT.RATE1;
            } else {
                if (LNCSCMRT.RATE1 != 0 
                    && LNCSCMRT.RATE2 != 0 
                    && LNCSCMRT.RATE3 == 0) {
                    LNCSCMRT.ALL_RATE = LNCSCMRT.RATE2;
                }
            }
            if (LNCSCMRT.RATE1 == LNCSCMRT.RATE2 
                && LNCSCMRT.RATE1 != 0 
                && LNCSCMRT.RATE2 != 0 
                && LNCSCMRT.RATE3 == 0) {
                LNCSCMRT.ALL_RATE = LNCSCMRT.RATE1;
            }
            if (LNCSCMRT.RATE1 != 0 
                && LNCSCMRT.RATE2 == 0 
                && LNCSCMRT.RATE3 == 0) {
                LNCSCMRT.ALL_RATE = LNCSCMRT.RATE1;
            }
            if (LNCSCMRT.RATE1 == 0 
                && LNCSCMRT.RATE2 != 0 
                && LNCSCMRT.RATE3 == 0) {
                LNCSCMRT.ALL_RATE = LNCSCMRT.RATE2;
            }
            if (LNCSCMRT.RATE1 == 0 
                && LNCSCMRT.RATE2 == 0 
                && LNCSCMRT.RATE3 != 0) {
                LNCSCMRT.ALL_RATE = LNCSCMRT.RATE3;
            }
        } else {
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void B_MPAG() throws IOException,SQLException,Exception {
    if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF ONL
        JIBS_tmp_str[9] = "SCZMPAG";
        Class<?>clazz = Class.forName(JIBS_tmp_str[9].trim());
        Object obj = clazz.newInstance();
        Method m = clazz.getDeclaredMethod("MP",new Class[]{SCCGWA.getClass(), SCCMPAG.getClass()});
        m.invoke(obj, SCCGWA, SCCMPAG);
        if (SCCGWA.COMM_AREA.EXCP_FLG == 'Y') {
            Z_RET();
            if (pgmRtn) return;
        }
    } else { //FROM #ELSE
    } //FROM #ENDIF
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
