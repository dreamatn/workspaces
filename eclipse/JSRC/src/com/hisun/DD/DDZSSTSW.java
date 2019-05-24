package com.hisun.DD;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.BP.BPCPNHIS;
import com.hisun.BP.BPCPQORG;
import com.hisun.CI.CICACCU;
import com.hisun.DC.DCCMSG_ERROR_MSG;
import com.hisun.DC.DCRCDDAT;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;

public class DDZSSTSW {
    boolean pgmRtn = false;
    String K_OUTPUT_FMT2 = "DD589";
    String K_OUTPUT_FMT3 = "DD590";
    String K_OUTPUT_FMT4 = "DD591";
    String K_HIS_REMARKS = "CREATE OR MODIFY ACCOUNT STS";
    int WS_OUT_CNT = 0;
    String WS_MSGID = " ";
    short WS_FLD_NO = 0;
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    DDCOSTSB DDCOSTSB = new DDCOSTSB();
    DDCOSTSI DDCOSTSI = new DDCOSTSI();
    DDCOSTSA DDCOSTSA = new DDCOSTSA();
    DDCOSTSM DDCOSTSM = new DDCOSTSM();
    CICACCU CICACCU = new CICACCU();
    BPCPQORG BPCQPORG = new BPCPQORG();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    DDRCCY DDRCCY = new DDRCCY();
    DDRMST DDRMST = new DDRMST();
    DDRSTSW DDRSTSW = new DDRSTSW();
    DCRCDDAT DCRCDDAT = new DCRCDDAT();
    SCCGWA SCCGWA;
    DDCSSTSW DDCSSTSW;
    public void MP(SCCGWA SCCGWA, DDCSSTSW DDCSSTSW) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DDCSSTSW = DDCSSTSW;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DDZSSTSW return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B150_CHECK_TYPE();
        if (pgmRtn) return;
        B160_GET_TR_BBR();
        if (pgmRtn) return;
        if (DDCSSTSW.FUNC == '0') {
            B200_BRW_STSW_REC_PROC();
            if (pgmRtn) return;
        } else if (DDCSSTSW.FUNC == '1') {
            B300_INQ_STSW_REC_PROC();
            if (pgmRtn) return;
        } else if (DDCSSTSW.FUNC == '2') {
            B400_ADD_STSW_REC_PROC();
            if (pgmRtn) return;
            B170_NON_FIN_HIS_PROC();
            if (pgmRtn) return;
        } else if (DDCSSTSW.FUNC == '3') {
            B500_MOD_STSW_REC_PROC();
            if (pgmRtn) return;
            B170_NON_FIN_HIS_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID TX TYPE(" + DDCSSTSW.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void B100_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCSSTSW.FUNC);
        CEP.TRC(SCCGWA, DDCSSTSW.AC_NO);
        CEP.TRC(SCCGWA, DDCSSTSW.AC_NM);
        CEP.TRC(SCCGWA, DDCSSTSW.REF_NO);
        CEP.TRC(SCCGWA, DDCSSTSW.TYPE);
        CEP.TRC(SCCGWA, DDCSSTSW.STS);
        CEP.TRC(SCCGWA, DDCSSTSW.EFF_DATE);
        CEP.TRC(SCCGWA, DDCSSTSW.EXP_DATE);
        CEP.TRC(SCCGWA, DDCSSTSW.REASON);
        if (DDCSSTSW.FUNC == ' ') {
            WS_MSGID = DDCMSG_ERROR_MSG.DD_FUNC_M_INPUT;
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        if (DDCSSTSW.FUNC != '0' 
            && DDCSSTSW.FUNC != '1' 
            && DDCSSTSW.FUNC != '2' 
            && DDCSSTSW.FUNC != '3') {
            WS_MSGID = DDCMSG_ERROR_MSG.DD_FUNC_INVALID;
            if (DDCSSTSW.FUNC == ' ') WS_FLD_NO = 0;
            else WS_FLD_NO = Short.parseShort(""+DDCSSTSW.FUNC);
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        if (DDCSSTSW.AC_NO.trim().length() == 0) {
            WS_MSGID = DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT;
            if (DDCSSTSW.AC_NO.trim().length() == 0) WS_FLD_NO = 0;
            else WS_FLD_NO = Short.parseShort(DDCSSTSW.AC_NO);
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        if (DDCSSTSW.FUNC == '0') {
            if (DDCSSTSW.REF_NO != 0) {
                WS_MSGID = DDCMSG_ERROR_MSG.DD_REFNO_CANT_INP;
                WS_FLD_NO = (short) DDCSSTSW.REF_NO;
                S000_ERR_MSG_PROC_CONTINUE();
                if (pgmRtn) return;
            }
        }
        if (DDCSSTSW.FUNC == '2') {
            if (DDCSSTSW.STS != 'N') {
                WS_MSGID = DDCMSG_ERROR_MSG.DD_STS_INPUT_ERR;
                WS_FLD_NO = (short) DDCSSTSW.REF_NO;
                S000_ERR_MSG_PROC_CONTINUE();
                if (pgmRtn) return;
            }
        }
        if (DDCSSTSW.FUNC == '1' 
            || DDCSSTSW.FUNC == '3') {
            if (DDCSSTSW.REF_NO == 0) {
                WS_MSGID = DDCMSG_ERROR_MSG.DD_REFNO_MUST_INP;
                WS_FLD_NO = (short) DDCSSTSW.REF_NO;
                S000_ERR_MSG_PROC_CONTINUE();
                if (pgmRtn) return;
            }
        }
        if (DDCSSTSW.FUNC == '2') {
            if (DDCSSTSW.EFF_DATE == 0) {
                DDCSSTSW.EFF_DATE = SCCGWA.COMM_AREA.AC_DATE;
            } else {
                if (DDCSSTSW.EFF_DATE != SCCGWA.COMM_AREA.AC_DATE) {
                    CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_EFF_DT_M_BE_ACDT);
                }
            }
            if (DDCSSTSW.EXP_DATE == 0) {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_EXP_DT_M_INPUT);
            } else {
                if (DDCSSTSW.EXP_DATE <= SCCGWA.COMM_AREA.AC_DATE) {
                    WS_MSGID = DDCMSG_ERROR_MSG.DD_EXP_DT_ERR;
                    WS_FLD_NO = (short) DDCSSTSW.EXP_DATE;
                    S000_ERR_MSG_PROC_CONTINUE();
                    if (pgmRtn) return;
                }
            }
            if (!DDCSSTSW.TYPE.equalsIgnoreCase("14") 
                && !DDCSSTSW.TYPE.equalsIgnoreCase("17") 
                && !DDCSSTSW.TYPE.equalsIgnoreCase("22") 
                && !DDCSSTSW.TYPE.equalsIgnoreCase("24") 
                && !DDCSSTSW.TYPE.equalsIgnoreCase("25") 
                && !DDCSSTSW.TYPE.equalsIgnoreCase("26") 
                && !DDCSSTSW.TYPE.equalsIgnoreCase("62")) {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_STSW_TYPE_INVALID);
            }
        }
        if (DDCSSTSW.EFF_DATE != 0 
            && DDCSSTSW.EXP_DATE != 0) {
            if (DDCSSTSW.EFF_DATE > DDCSSTSW.EXP_DATE) {
                WS_MSGID = DDCMSG_ERROR_MSG.DD_EFF_M_LT_EXP;
                WS_FLD_NO = (short) DDCSSTSW.EFF_DATE;
                S000_ERR_MSG_PROC_CONTINUE();
                if (pgmRtn) return;
            }
        }
        WS_MSGID = DDCMSG_ERROR_MSG.DD_INPUT_DATA_ERR;
        S000_ERR_MSG_PROC_LAST();
        if (pgmRtn) return;
    }
    public void B150_CHECK_TYPE() throws IOException,SQLException,Exception {
        if (DDCSSTSW.TYPE.equalsIgnoreCase("14") 
            || DDCSSTSW.TYPE.equalsIgnoreCase("24") 
            || DDCSSTSW.TYPE.equalsIgnoreCase("22") 
            || DDCSSTSW.TYPE.equalsIgnoreCase("62")) {
            B160_CHCK_AC_CI_INF();
            if (pgmRtn) return;
        }
        B170_CHCK_AC_FRM_APP();
        if (pgmRtn) return;
    }
    public void B160_GET_TR_BBR() throws IOException,SQLException,Exception {
