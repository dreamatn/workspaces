package com.hisun.TD;

import com.hisun.CI.*;
import com.hisun.SC.*;
import com.hisun.SC.SCCBINF;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCCLDT;
import com.hisun.SC.SCCCWA;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;
import com.hisun.SC.SCCSUBS;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class TDZZRCX {
    DBParm TDTMISE_RD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_OUTPUT_FMT = "TD591";
    int K_OUTPUT_ROW = 5;
    String K_PRDP_TYP = "PRDPR";
    int K_MAX_ROW = 5;
    int K_MAX_COL = 0;
    int K_COL_CNT = 0;
    String PGM_SCSSCLDT = "SCSSCLDT";
    String WS_ERR_MSG = " ";
    int WS_I = 0;
    int WS_K = 0;
    int WS_T = 0;
    int WS_TT = 0;
    int WS_CNT = 0;
    char WS_MISE_FLG = ' ';
    TDCMSG_ERROR_MSG TDCMSG_ERROR_MSG = new TDCMSG_ERROR_MSG();
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCBINF SCCBINF = new SCCBINF();
    SCCFMT SCCFMT = new SCCFMT();
    SCCSUBS SCCSUBS = new SCCSUBS();
    CICCUST CICCUST = new CICCUST();
    SCCCLDT SCCCLDT = new SCCCLDT();
    TDRMISE TDRMISE = new TDRMISE();
    TDCOZRCX TDCOZRCX = new TDCOZRCX();
    SCCGWA SCCGWA;
    SCCCWA SCCCWA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    TDCZRCX TDCZRCX;
    public void MP(SCCGWA SCCGWA, TDCZRCX TDCZRCX) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.TDCZRCX = TDCZRCX;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "TDZZRCX return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        IBS.init(SCCGWA, TDRMISE);
        IBS.init(SCCGWA, CICCUST);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT_PROC();
        if (pgmRtn) return;
        B200_GET_MSG_PROC();
        if (pgmRtn) return;
    }
    public void B100_CHECK_INPUT_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDCZRCX.OAC_NO);
        CEP.TRC(SCCGWA, TDCZRCX.OAC_SEQ);
        CEP.TRC(SCCGWA, TDCZRCX.NAC_NO);
        CEP.TRC(SCCGWA, TDCZRCX.NAC_SEQ);
        if (TDCZRCX.OAC_NO.trim().length() == 0 
            && TDCZRCX.NAC_NO.trim().length() == 0) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_ONAC_M_INPUT);
        }
        if (TDCZRCX.OAC_NO.trim().length() == 0) {
            TDRMISE.NAC_NO = TDCZRCX.NAC_NO;
            IBS.init(SCCGWA, CICCUST);
            CICCUST.FUNC = 'A';
            CICCUST.DATA.AGR_NO = TDCZRCX.NAC_NO;
            CEP.TRC(SCCGWA, "2222222222222");
            S000_CALL_CIZCUST();
            if (pgmRtn) return;
            if (CICCUST.O_DATA.O_CI_TYP == '1') {
            } else {
                if (TDCZRCX.NAC_SEQ != 0) {
                    TDRMISE.NAC_SEQ = TDCZRCX.NAC_SEQ;
                } else {
                    CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_MUST_IPT_AC_SEQ);
                }
            }
            T000_READ_TDTMISE1();
            if (pgmRtn) return;
        } else {
            if (TDCZRCX.NAC_NO.trim().length() == 0) {
                TDRMISE.KEY.OAC_NO = TDCZRCX.OAC_NO;
                IBS.init(SCCGWA, CICCUST);
                CICCUST.FUNC = 'A';
                CICCUST.DATA.AGR_NO = TDCZRCX.OAC_NO;
                CEP.TRC(SCCGWA, "333333333333");
                S000_CALL_CIZCUST();
                if (pgmRtn) return;
                if (CICCUST.O_DATA.O_CI_TYP == '1') {
                } else {
                    if (TDCZRCX.OAC_SEQ != 0) {
                        TDRMISE.OAC_SEQ = TDCZRCX.OAC_SEQ;
                    } else {
                        CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_MUST_IPT_AC_SEQ);
                    }
                }
                T000_READ_TDTMISE2();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, "222222");
            } else {
                TDRMISE.KEY.OAC_NO = TDCZRCX.OAC_NO;
                IBS.init(SCCGWA, CICCUST);
                CICCUST.FUNC = 'A';
                CICCUST.DATA.AGR_NO = TDCZRCX.OAC_NO;
                CEP.TRC(SCCGWA, "4444444444444444");
                S000_CALL_CIZCUST();
                if (pgmRtn) return;
                if (CICCUST.O_DATA.O_CI_TYP == '1') {
                } else {
                    if (TDCZRCX.OAC_SEQ != 0) {
                        TDRMISE.OAC_SEQ = TDCZRCX.OAC_SEQ;
                    } else {
                        CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_MUST_IPT_AC_SEQ);
                    }
                }
                TDRMISE.NAC_NO = TDCZRCX.NAC_NO;
                IBS.init(SCCGWA, CICCUST);
                CICCUST.FUNC = 'A';
                CICCUST.DATA.AGR_NO = TDCZRCX.NAC_NO;
                S000_CALL_CIZCUST();
                if (pgmRtn) return;
                if (CICCUST.O_DATA.O_CI_TYP == '1') {
                } else {
                    if (TDCZRCX.NAC_SEQ != 0) {
                        TDRMISE.NAC_SEQ = TDCZRCX.NAC_SEQ;
                    } else {
                        CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_MUST_IPT_AC_SEQ);
                    }
                }
                T000_READ_TDTMISE3();
                if (pgmRtn) return;
            }
        }
        CEP.TRC(SCCGWA, "333333");
        if (WS_MISE_FLG == 'N') {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_AC_NOT_IN_MISE);
        }
    }
    public void T000_READ_TDTMISE1() throws IOException,SQLException,Exception {
        TDTMISE_RD = new DBParm();
        TDTMISE_RD.TableName = "TDTMISE";
        TDTMISE_RD.where = "NAC_NO = :TDRMISE.NAC_NO "
            + "AND NAC_SEQ = :TDRMISE.NAC_SEQ";
        TDTMISE_RD.errhdl = true;
        IBS.READ(SCCGWA, TDRMISE, this, TDTMISE_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_MISE_FLG = 'N';
        }
    }
    public void T000_READ_TDTMISE3() throws IOException,SQLException,Exception {
        TDTMISE_RD = new DBParm();
        TDTMISE_RD.TableName = "TDTMISE";
        TDTMISE_RD.where = "OAC_NO = :TDRMISE.KEY.OAC_NO "
            + "AND NAC_NO = :TDRMISE.NAC_NO "
            + "AND NAC_SEQ = :TDRMISE.NAC_SEQ "
            + "AND OAC_SEQ = :TDRMISE.OAC_SEQ";
        TDTMISE_RD.errhdl = true;
        IBS.READ(SCCGWA, TDRMISE, this, TDTMISE_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_MISE_FLG = 'N';
        }
    }
    public void T000_READ_TDTMISE2() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDRMISE.KEY.OAC_NO);
        CEP.TRC(SCCGWA, TDRMISE.OAC_SEQ);
        TDTMISE_RD = new DBParm();
        TDTMISE_RD.TableName = "TDTMISE";
        TDTMISE_RD.where = "OAC_NO = :TDRMISE.KEY.OAC_NO "
            + "AND OAC_SEQ = :TDRMISE.OAC_SEQ";
        TDTMISE_RD.errhdl = true;
        IBS.READ(SCCGWA, TDRMISE, this, TDTMISE_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_MISE_FLG = 'N';
        }
        CEP.TRC(SCCGWA, "11111111");
    }
    public void B200_GET_MSG_PROC() throws IOException,SQLException,Exception {
        TDCOZRCX.OAC_NO = TDRMISE.KEY.OAC_NO;
        TDCOZRCX.OAC_SEQ = TDRMISE.OAC_SEQ;
        TDCOZRCX.OCI_NO = TDRMISE.KEY.OCI_NO;
        IBS.init(SCCGWA, CICCUST);
        CICCUST.FUNC = 'C';
        CICCUST.DATA.CI_NO = TDRMISE.KEY.OCI_NO;
        CEP.TRC(SCCGWA, TDRMISE.KEY.OCI_NO);
        S000_CALL_CIZCUST();
        if (pgmRtn) return;
        TDCOZRCX.OCI_NM = CICCUST.O_DATA.O_CI_NM;
        TDCOZRCX.NAC_NO = TDRMISE.NAC_NO;
        TDCOZRCX.NAC_SEQ = TDRMISE.NAC_SEQ;
        TDCOZRCX.NCI_NO = TDRMISE.NCI_NO;
        IBS.init(SCCGWA, CICCUST);
        CICCUST.FUNC = 'C';
        CICCUST.DATA.CI_NO = TDRMISE.NCI_NO;
        CEP.TRC(SCCGWA, "11111111111");
        S000_CALL_CIZCUST();
        if (pgmRtn) return;
        TDCOZRCX.NCI_NM = CICCUST.O_DATA.O_CI_NM;
        TDCOZRCX.MSE_DT = TDRMISE.MSE_DT;
        TDCOZRCX.MSE_BAL = TDRMISE.MSE_BAL;
        TDCOZRCX.SUG_BAL = TDRMISE.SUG_BAL;
        TDCOZRCX.BAL = TDRMISE.BAL;
        TDCOZRCX.INT_BAL = TDRMISE.INT_BAL;
        TDCOZRCX.OPE_TLR = TDRMISE.OPE_TLR;
        TDCOZRCX.MSE_TME = TDRMISE.MSE_NUM;
        R000_OUT_PROC();
        if (pgmRtn) return;
    }
    public void S000_CALL_CIZCUST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-CUST", CICCUST);
        if (CICCUST.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICCUST.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void R000_OUT_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDCOZRCX.OAC_NO);
        CEP.TRC(SCCGWA, TDCOZRCX.OAC_SEQ);
        CEP.TRC(SCCGWA, TDCOZRCX.OCI_NO);
        CEP.TRC(SCCGWA, TDCOZRCX.OCI_NM);
        CEP.TRC(SCCGWA, TDCOZRCX.NAC_NO);
        CEP.TRC(SCCGWA, TDCOZRCX.NAC_SEQ);
        CEP.TRC(SCCGWA, TDCOZRCX.NCI_NO);
        CEP.TRC(SCCGWA, TDCOZRCX.NCI_NM);
        CEP.TRC(SCCGWA, TDCOZRCX.MSE_DT);
        CEP.TRC(SCCGWA, TDCOZRCX.MSE_BAL);
        CEP.TRC(SCCGWA, TDCOZRCX.SUG_BAL);
        CEP.TRC(SCCGWA, TDCOZRCX.BAL);
        CEP.TRC(SCCGWA, TDCOZRCX.INT_BAL);
        CEP.TRC(SCCGWA, TDCOZRCX.OPE_TLR);
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = TDCOZRCX;
        SCCFMT.DATA_LEN = 694;
        IBS.FMT(SCCGWA, SCCFMT);
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
