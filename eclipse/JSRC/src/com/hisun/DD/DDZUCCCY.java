package com.hisun.DD;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.AI.AICUUPIA;
import com.hisun.BP.BPCFFCON;
import com.hisun.BP.BPCFFTXI;
import com.hisun.BP.BPCFSCHG;
import com.hisun.BP.BPCGCFEE;
import com.hisun.BP.BPCGPFEE;
import com.hisun.BP.BPCPRGST;
import com.hisun.BP.BPCTCALF;
import com.hisun.BP.BPCUSBOX;
import com.hisun.BP.BPZFSCHG;
import com.hisun.CI.CICQACAC;
import com.hisun.DC.DCCPACTY;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;

public class DDZUCCCY {
    boolean pgmRtn = false;
    String K_OUTPUT_FMT = "DD101";
    String K_OUTPUT_FMT2 = "DD111";
    String K_SVR_CD = "DD-SVR-CLEAR-CCY";
    String K_CR_MMO = "A001";
    String K_HIS_MMO = "S101";
    String WS_ERR_MSG = " ";
    double WS_TRF_AMT = 0;
    double WS_CLS_BAL = 0;
    double WS_CLR_BAL = 0;
    char WS_CROS_CR_FLG = ' ';
    String WS_POST_CI = " ";
    String WS_TRF_CI = " ";
    String WS_STMT_CI = " ";
    short WS_IDX = 0;
    double WS_FEE_AMT = 0;
    char WS_FEE_DR_FLG = ' ';
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCUSBOX BPCUSBOX = new BPCUSBOX();
    DDCUPINT DDCUPINT = new DDCUPINT();
    DDCUCRAC DDCUCRAC = new DDCUCRAC();
    DDCUDRAC DDCUDRAC = new DDCUDRAC();
    DDCOCCCY DDCOCCCY = new DDCOCCCY();
    DDCOPINT DDCOPINT = new DDCOPINT();
    BPCFFCON BPCFFCON = new BPCFFCON();
    BPCFFTXI BPCFFTXI = new BPCFFTXI();
    BPCFSCHG BPCFSCHG = new BPCFSCHG();
    AICUUPIA AICUUPIA = new AICUUPIA();
    DDRMST DDRMST = new DDRMST();
    DDRCCY DDRCCY = new DDRCCY();
    BPCPRGST BPCPRGST = new BPCPRGST();
    BPCTCALF BPCTCALF = new BPCTCALF();
    DCCPACTY DCCPACTY = new DCCPACTY();
    CICQACAC CICQACAC = new CICQACAC();
    AIRMIB AIRMIB = new AIRMIB();
    AIRCMIB AIRCMIB = new AIRCMIB();
    SCCGWA SCCGWA;
    DDCUCCCY DDCUCCCY;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCGCFEE BPCGCFEE;
    BPCGPFEE BPCGPFEE;
    public void MP(SCCGWA SCCGWA, DDCUCCCY DDCUCCCY) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DDCUCCCY = DDCUCCCY;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DDZUCCCY return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B015_INQ_INT_PROC();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            B020_POST_INT_PROC();
            if (pgmRtn) return;
            if (DDCUCCCY.PAY_MTH == 'F') {
                B030_CHG_CLS_FEE_PROC();
                if (pgmRtn) return;
                B042_CHG_FEE_PROC();
                if (pgmRtn) return;
            }
            if ((DDCUCCCY.CROS_DR_FLG == '0' 
                || DDCUCCCY.CROS_DR_FLG == '1') 
                && DDCUCCCY.PAY_MTH != 'O' 
                && DDCUCCCY.PAY_MTH != 'F') {
                if (SCCGWA.COMM_AREA.BR_DP.TR_BRANCH != DDCUCCCY.DOMICILE_BR 
                    && DDCUCCCY.PRIN != 0) {
                    B035_GET_BR_CITY_FLG();
                    if (pgmRtn) return;
                    B040_CLAC_FEE_PROC();
                    if (pgmRtn) return;
                    WS_FEE_DR_FLG = 'Y';
                }
            }
            if (DDCUCCCY.PAY_MTH == 'T' 
                && DDCUCCCY.TRF_STLT != '3') {
                B036_GET_OTH_AC_INF();
                if (pgmRtn) return;
                if ((WS_CROS_CR_FLG == '0' 
                    || WS_CROS_CR_FLG == '1') 
                    && SCCGWA.COMM_AREA.BR_DP.TR_BRANCH != DDRCCY.OWNER_BRDP 
                    && CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO.trim().length() > 0 
                    && DDCUCCCY.PRIN != 0) {
                    B037_GET_BR_CITY_FLG();
                    if (pgmRtn) return;
                    B045_CLAC_FEE_PROC();
                    if (pgmRtn) return;
                    WS_FEE_DR_FLG = 'Y';
                }
            }
            if (WS_FEE_DR_FLG == 'Y') {
                B042_CHG_FEE_PROC();
                if (pgmRtn) return;
            }
            B049_CHECK_BAL_PROC();
            if (pgmRtn) return;
            if (WS_CLR_BAL != 0 
                || DDCUCCCY.PAY_MTH != ' ') {
                B050_CLEAR_CCY_PROC();
                if (pgmRtn) return;
            }
        } else {
            B050_CLEAR_CCY_PROC();
            if (pgmRtn) return;
            B020_POST_INT_PROC();
            if (pgmRtn) return;
        }
        if (WS_CLS_BAL != 0 
            && DDCUCCCY.PAY_MTH != 'F') {
            if (DDCUCCCY.PAY_MTH == 'T') {
                if (DDCUCCCY.TRF_STLT != '3') {
                    B060_TRF_CLS_AMT_PROC();
                    if (pgmRtn) return;
                } else {
                    B065_TRF_AI_AMT_PROC();
                    if (pgmRtn) return;
                }
            } else {
                B070_SUB_CASH_BOX_PROC();
                if (pgmRtn) return;
            }
        }
        B150_TRANS_DATA_OUTPUT();
        if (pgmRtn) return;
        B151_DATA_OUTPUT();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCUCCCY.AC);
        CEP.TRC(SCCGWA, DDCUCCCY.CARD_NO);
        CEP.TRC(SCCGWA, DDCUCCCY.CCY);
        CEP.TRC(SCCGWA, DDCUCCCY.CCY_TYPE);
        CEP.TRC(SCCGWA, DDCUCCCY.PAY_MTH);
        CEP.TRC(SCCGWA, DDCUCCCY.TRF_AC);
        CEP.TRC(SCCGWA, DDCUCCCY.TRF_CARD);
        CEP.TRC(SCCGWA, DDCUCCCY.REMARK);
        CEP.TRC(SCCGWA, DDCUCCCY.NARRATIVE);
        CEP.TRC(SCCGWA, DDCUCCCY.PAY_TYPE);
        CEP.TRC(SCCGWA, DDCUCCCY.ID_TYPE);
        CEP.TRC(SCCGWA, DDCUCCCY.ID_NO);
        CEP.TRC(SCCGWA, DDCUCCCY.PAY_SIGN_NO);
        CEP.TRC(SCCGWA, DDCUCCCY.DOMICILE_BR);
        CEP.TRC(SCCGWA, DDCUCCCY.CROS_DR_FLG);
        CEP.TRC(SCCGWA, DDCUCCCY.TRF_AC);
        CEP.TRC(SCCGWA, DDCUCCCY.TRF_STLT);
        CEP.TRC(SCCGWA, DDCUCCCY.PRIN);
        if (DDCUCCCY.AC.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DDCUCCCY.PAY_MTH == 'T') {
            if (DDCUCCCY.TRF_AC.trim().length() == 0 
                && DDCUCCCY.TRF_CARD.trim().length() == 0) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_TRF_AC_NO_M_INPUT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (DDCUCCCY.CCY.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CCY_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DDCUCCCY.CCY_TYPE == ' ') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_CCY_TYPE_M_INPUT);
        }
    }
    public void B015_INQ_INT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCUPINT);
        DDCUPINT.CARD_NO = DDCUCCCY.CARD_NO;
        DDCUPINT.AC = DDCUCCCY.AC;
        DDCUPINT.CCY = DDCUCCCY.CCY;
        DDCUPINT.CCY_TYPE = DDCUCCCY.CCY_TYPE;
        DDCUPINT.TX_TYP = 'I';
        CEP.TRC(SCCGWA, DDCUPINT.CCY);
        CEP.TRC(SCCGWA, DDCUPINT.CCY_TYPE);
        S000_CALL_DDZUPINT();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DDCUPINT.INT_TAX);
        CEP.TRC(SCCGWA, DDCUPINT.OD_INT);
        CEP.TRC(SCCGWA, DDCUPINT.DEP_INT);
        CEP.TRC(SCCGWA, DDCUPINT.UOD_INT);
    }
    public void B020_POST_INT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCUPINT);
        DDCUPINT.CARD_NO = DDCUCCCY.CARD_NO;
        DDCUPINT.AC = DDCUCCCY.AC;
        DDCUPINT.CCY = DDCUCCCY.CCY;
        DDCUPINT.CCY_TYPE = DDCUCCCY.CCY_TYPE;
        DDCUPINT.TX_MMO = K_HIS_MMO;
        DDCUPINT.REMARK = DDCUCCCY.REMARK;
        DDCUPINT.TX_TYP = 'O';
        CEP.TRC(SCCGWA, DDCUPINT.CCY);
        CEP.TRC(SCCGWA, DDCUPINT.CCY_TYPE);
        S000_CALL_DDZUPINT();
        if (pgmRtn) return;
    }
    public void B030_CHG_CLS_FEE_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCUCCCY.FEE_AMT);
        IBS.init(SCCGWA, BPCFFTXI);
        BPCFFTXI.TX_DATA.AUH_FLG = '0';
        BPCFFTXI.TX_DATA.CI_NO = DDCUCCCY.CI_NO;
        BPCFFTXI.TX_DATA.CHG_AC_INFO.CHG_AC = DDCUCCCY.AC;
        if (DDCUCCCY.CARD_NO.trim().length() > 0) {
            BPCFFTXI.TX_DATA.CARD_PSBK_NO = DDCUCCCY.CARD_NO;
        }
        BPCFFTXI.TX_DATA.CHG_AC_INFO.CHG_CCY = DDCUCCCY.CCY;
        BPCFFTXI.TX_DATA.CCY_TYPE = DDCUCCCY.CCY_TYPE;
        S000_CALL_BPZFFTXI();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "TESTING");
        IBS.init(SCCGWA, BPCFFCON);
        BPCFFCON.FEE_INFO.FEE_CNT = 1;
        BPCFFCON.FEE_INFO.FEE_INFO1[1-1].TO_ACCT_CEN = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCFFCON.FEE_INFO.FEE_INFO1[1-1].CHG_AC_TY = '0';
        BPCFFCON.FEE_INFO.FEE_INFO1[1-1].CHG_AC = DDCUCCCY.AC;
        BPCFFCON.FEE_INFO.FEE_INFO1[1-1].FEE_CODE = "MA001";
        BPCFFCON.FEE_INFO.FEE_INFO1[1-1].FEE_CCY = DDCUCCCY.CCY;
        BPCFFCON.FEE_INFO.FEE_INFO1[1-1].CHG_CCY = DDCUCCCY.CCY;
        BPCFFCON.FEE_INFO.FEE_INFO1[1-1].FEE_BAS = DDCUCCCY.FEE_AMT;
        BPCFFCON.FEE_INFO.FEE_INFO1[1-1].FEE_AMT = DDCUCCCY.FEE_AMT;
        BPCFFCON.FEE_INFO.FEE_INFO1[1-1].CHG_BAS = DDCUCCCY.FEE_AMT;
        BPCFFCON.FEE_INFO.FEE_INFO1[1-1].CHG_AMT = DDCUCCCY.FEE_AMT;
        BPCFFCON.FEE_INFO.FEE_INFO1[1-1].ADJ_AMT = DDCUCCCY.FEE_AMT;
        BPCFFCON.FEE_INFO.FEE_INFO1[1-1].CHG_FLG = '0';
        S000_CALL_BPZFFCON();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZFFCON() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-F-F-CON-CHG-INFO", BPCFFCON);
        if (BPCFFCON.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFFCON.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZFFTXI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-F-F-TX-INFO", BPCFFTXI);
        if (BPCFFTXI.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFFTXI.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZFSCHG() throws IOException,SQLException,Exception {
        BPZFSCHG BPZFSCHG = new BPZFSCHG();
        BPZFSCHG.MP(SCCGWA, null);
    }
    public void B035_GET_BR_CITY_FLG() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "AC-BR-INF");
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.BR_DP.TR_BRANCH);
        CEP.TRC(SCCGWA, DDCUCCCY.DOMICILE_BR);
        IBS.init(SCCGWA, BPCPRGST);
        BPCPRGST.BR1 = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCPRGST.BR2 = DDCUCCCY.DOMICILE_BR;
        S000_CALL_BPZPRGST();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCPRGST.BRANCH_FLG);
    }
    public void B036_GET_OTH_AC_INF() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "OTH-AC-BR-INF");
        IBS.init(SCCGWA, CICQACAC);
        CICQACAC.FUNC = 'C';
        if (DDCUCCCY.TRF_AC.trim().length() > 0) {
            CICQACAC.DATA.AGR_NO = DDCUCCCY.TRF_AC;
        } else {
            CICQACAC.DATA.AGR_NO = DDCUCCCY.TRF_CARD;
        }
        CICQACAC.DATA.CCY_ACAC = DDCUCCCY.CCY;
        CICQACAC.DATA.CR_FLG = DDCUCCCY.CCY_TYPE;
        S000_CALL_CIZQACAC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO);
        if (CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO.trim().length() > 0) {
            IBS.init(SCCGWA, DDRCCY);
            DDRCCY.KEY.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
            T000_READ_DDTCCY();
            if (pgmRtn) return;
        }
        if (CICQACAC.O_DATA.O_ACR_DATA.O_FRM_APP_ACR.equalsIgnoreCase("DD")) {
            IBS.init(SCCGWA, DDRMST);
            DDRMST.KEY.CUS_AC = DDCUCCCY.TRF_AC;
            T000_READ_UPDATE_DDTMST();
            if (pgmRtn) return;
            WS_CROS_CR_FLG = DDRMST.CROS_CR_FLG;
        } else {
            if (CICQACAC.O_DATA.O_ACR_DATA.O_FRM_APP_ACR.equalsIgnoreCase("DC")) {
                WS_CROS_CR_FLG = '1';
            }
        }
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.BR_DP.TR_BRANCH);
        CEP.TRC(SCCGWA, DDRMST.CROS_CR_FLG);
        CEP.TRC(SCCGWA, DDRMST.CI_TYP);
    }
    public void B037_GET_BR_CITY_FLG() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPRGST);
        BPCPRGST.BR1 = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCPRGST.BR2 = DDRCCY.OWNER_BRDP;
        S000_CALL_BPZPRGST();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCPRGST.BRANCH_FLG);
    }
    public void S000_CALL_BPZPRGST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG-STATION", BPCPRGST);
        if (BPCPRGST.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPRGST.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZQACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACAC", CICQACAC);
        if (CICQACAC.RC.RC_CODE != 0 
            && CICQACAC.DATA.CCY_ACAC.equalsIgnoreCase("156")) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICQACAC.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_UPDATE_DDTMST() throws IOException,SQLException,Exception {
        DDTMST_RD = new DBParm();
        DDTMST_RD.TableName = "DDTMST";
        IBS.READ(SCCGWA, DDRMST, DDTMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_MST1_REC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_DDTCCY() throws IOException,SQLException,Exception {
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        IBS.READ(SCCGWA, DDRCCY, DDTCCY_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CCY2_REC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_READUPD_DDTCCY() throws IOException,SQLException,Exception {
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        DDTCCY_RD.where = "CUS_AC = :DDRCCY.CUS_AC "
            + "AND CCY = :DDRCCY.CCY "
            + "AND CCY_TYPE = :DDRCCY.CCY_TYPE";
        DDTCCY_RD.upd = true;
        IBS.READ(SCCGWA, DDRCCY, this, DDTCCY_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CCY2_REC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_DDTCCY() throws IOException,SQLException,Exception {
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        IBS.REWRITE(SCCGWA, DDRCCY, DDTCCY_RD);
    }
    public void R000_SET_DR_FEE_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFFTXI);
        BPCFFTXI.TX_DATA.AUH_FLG = '0';
        BPCFFTXI.TX_DATA.CHG_AC_INFO.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        CEP.TRC(SCCGWA, DDCUCCCY.AC);
        CEP.TRC(SCCGWA, DDCUCCCY.CARD_NO);
        CEP.TRC(SCCGWA, DDCUCCCY.BV_TYP);
        if (DDCUCCCY.CARD_NO.trim().length() > 0) {
            if (DDCUCCCY.BV_TYP == '1') {
                BPCFFTXI.TX_DATA.CHG_AC_INFO.AC_TYP = '4';
            }
            if (DDCUCCCY.BV_TYP == '2') {
                BPCFFTXI.TX_DATA.CHG_AC_INFO.AC_TYP = '5';
            }
            BPCFFTXI.TX_DATA.CARD_PSBK_NO = DDCUCCCY.CARD_NO;
        } else {
            BPCFFTXI.TX_DATA.CHG_AC_INFO.AC_TYP = '0';
            BPCFFTXI.TX_DATA.CHG_AC_INFO.CHG_AC = DDCUCCCY.AC;
        }
        BPCFFTXI.TX_DATA.CHG_AC_INFO.CHG_CCY = DDCUCCCY.CCY;
        BPCFFTXI.TX_DATA.CCY_TYPE = DDCUCCCY.CCY_TYPE;
        BPCFFTXI.TX_DATA.SVR_CD = SCCGWA.COMM_AREA.SERV_CODE;
        BPCFFTXI.TX_DATA.CI_NO = DDCUCCCY.CI_NO;
        CEP.TRC(SCCGWA, BPCFFTXI.TX_DATA.CHG_AC_INFO.CHG_CCY);
        CEP.TRC(SCCGWA, BPCFFTXI.TX_DATA.CHG_AC_INFO.AC_TYP);
        S000_CALL_BPZFFTXI();
        if (pgmRtn) return;
    }
    public void B040_CLAC_FEE_PROC() throws IOException,SQLException,Exception {
        R000_SET_DR_FEE_INFO();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPCTCALF);
        BPCTCALF.INPUT_AREA.FUNC_CODE = 'C';
        BPCTCALF.INPUT_AREA.ATTR_VAL.BNK_FLG = '0';
        if (BPCPRGST.BRANCH_FLG == 'Y') {
            BPCTCALF.INPUT_AREA.ATTR_VAL.CITY_FLG = '0';
        } else {
            BPCTCALF.INPUT_AREA.ATTR_VAL.CITY_FLG = '1';
        }
        CEP.TRC(SCCGWA, BPCTCALF.INPUT_AREA.ATTR_VAL.CITY_FLG);
        if (DDCUCCCY.CARD_NO.trim().length() > 0) {
            BPCTCALF.INPUT_AREA.TX_AC = DDCUCCCY.CARD_NO;
        } else {
            BPCTCALF.INPUT_AREA.TX_AC = DDCUCCCY.AC;
        }
        BPCTCALF.INPUT_AREA.TX_CCY = DDCUCCCY.CCY;
        if ("1".trim().length() == 0) BPCTCALF.INPUT_AREA.TX_CNT = 0;
        else BPCTCALF.INPUT_AREA.TX_CNT = Short.parseShort("1");
        BPCTCALF.INPUT_AREA.TX_AMT = DDCUCCCY.PRIN;
        BPCTCALF.INPUT_AREA.OTHER_AC = DDCUCCCY.TRF_AC;
        CEP.TRC(SCCGWA, DDRMST.PROD_CODE);
        BPCTCALF.INPUT_AREA.PROD_CODE1 = DDRMST.PROD_CODE;
        BPCTCALF.INPUT_AREA.ATTR_VAL.DC_FLG = '0';
        S000_CALL_BPZFCALF();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCTCALF.OUTPUT_AREA.FEE_INFO[1-1].CHG_AC_TY);
        CEP.TRC(SCCGWA, BPCTCALF.OUTPUT_AREA.FEE_INFO[1-1].CHG_AC);
        CEP.TRC(SCCGWA, BPCTCALF.OUTPUT_AREA.FEE_INFO[1-1].ADJ_AMT);
        CEP.TRC(SCCGWA, DDCUCCCY.AC);
        CEP.TRC(SCCGWA, DDCUCCCY.CARD_NO);
    }
    public void B042_CHG_FEE_PROC() throws IOException,SQLException,Exception {
        S000_CALL_BPZFSCHG();
        if (pgmRtn) return;
    }
    public void B045_CLAC_FEE_PROC() throws IOException,SQLException,Exception {
        R000_SET_DR_FEE_INFO();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPCTCALF);
        BPCTCALF.INPUT_AREA.FUNC_CODE = 'C';
        BPCTCALF.INPUT_AREA.ATTR_VAL.BNK_FLG = '0';
        if (BPCPRGST.BRANCH_FLG == 'Y') {
            BPCTCALF.INPUT_AREA.ATTR_VAL.CITY_FLG = '0';
        } else {
            BPCTCALF.INPUT_AREA.ATTR_VAL.CITY_FLG = '1';
        }
        if (DDCUCCCY.TRF_CARD.trim().length() > 0) {
            BPCTCALF.INPUT_AREA.TX_AC = DDCUCCCY.TRF_CARD;
        } else {
            BPCTCALF.INPUT_AREA.TX_AC = DDCUCCCY.TRF_AC;
        }
        BPCTCALF.INPUT_AREA.TX_CCY = DDCUCCCY.CCY;
        BPCTCALF.INPUT_AREA.TX_AMT = DDCUCCCY.PRIN;
        if ("1".trim().length() == 0) BPCTCALF.INPUT_AREA.TX_CNT = 0;
        else BPCTCALF.INPUT_AREA.TX_CNT = Short.parseShort("1");
        BPCTCALF.INPUT_AREA.OTHER_AC = DDCUCCCY.TRF_AC;
        CEP.TRC(SCCGWA, BPCTCALF.INPUT_AREA.OTHER_AC);
        CEP.TRC(SCCGWA, BPCTCALF.INPUT_AREA.TX_CI);
        CEP.TRC(SCCGWA, DDRMST.PROD_CODE);
        BPCTCALF.INPUT_AREA.PROD_CODE1 = DDRMST.PROD_CODE;
        BPCTCALF.INPUT_AREA.ATTR_VAL.DC_FLG = '1';
        CEP.TRC(SCCGWA, BPCTCALF.RC);
        S000_CALL_BPZFCALF();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCTCALF.RC);
        CEP.TRC(SCCGWA, BPCTCALF.OUTPUT_AREA.FEE_INFO[2-1].CHG_AC_TY);
        CEP.TRC(SCCGWA, BPCTCALF.OUTPUT_AREA.FEE_INFO[2-1].CHG_AC);
        CEP.TRC(SCCGWA, DDCUCCCY.TRF_AC);
        CEP.TRC(SCCGWA, DDCUCCCY.TRF_CARD);
        CEP.TRC(SCCGWA, BPCTCALF.OUTPUT_AREA.FEE_INFO[2-1].ADJ_AMT);
    }
    public void S000_CALL_BPZFCALF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-F-F-CAL-FEE", BPCTCALF);
    }
    public void B049_CHECK_BAL_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRCCY);
        if (DDCUCCCY.AC.trim().length() > 0) {
            DDRCCY.CUS_AC = DDCUCCCY.AC;
        } else {
            DDRCCY.CUS_AC = DDCUCCCY.CARD_NO;
        }
        DDRCCY.CCY = DDCUCCCY.CCY;
        DDRCCY.CCY_TYPE = DDCUCCCY.CCY_TYPE;
        T000_READUPD_DDTCCY();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DDRCCY.CURR_BAL);
        CEP.TRC(SCCGWA, DDRCCY.CCAL_TOT_BAL);
        WS_CLR_BAL = DDRCCY.CURR_BAL + DDRCCY.CCAL_TOT_BAL;
        CEP.TRC(SCCGWA, WS_CLR_BAL);
        if (WS_CLR_BAL == 0 
            && DDCUCCCY.PAY_MTH == ' ') {
            DDRCCY.STS = 'C';
            if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
            JIBS_tmp_int = DDRCCY.STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
            DDRCCY.STS_WORD = DDRCCY.STS_WORD.substring(0, 11 - 1) + "1" + DDRCCY.STS_WORD.substring(11 + 1 - 1);
            DDRCCY.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DDRCCY.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
            T000_REWRITE_DDTCCY();
            if (pgmRtn) return;
        }
        if (WS_CLR_BAL != 0 
            && DDCUCCCY.PAY_MTH == ' ') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_PAY_MTH_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B050_CLEAR_CCY_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCUDRAC);
        DDCUDRAC.BV_TYP = DDCUCCCY.BV_TYP;
        CEP.TRC(SCCGWA, DDCUDRAC.BV_TYP);
        if (DDCUCCCY.PAY_MTH == 'C') {
            DDCUDRAC.TX_TYPE = 'C';
        } else {
            DDCUDRAC.TX_TYPE = 'T';
        }
        DDCUDRAC.TX_MMO = DDCUCCCY.TX_MMO;
        DDCUDRAC.GD_WITHDR_FLG = 'Y';
        DDCUDRAC.AC = DDCUCCCY.AC;
        if (DDCUCCCY.TRF_AC.trim().length() > 0) {
            DDCUDRAC.OTHER_AC = DDCUCCCY.TRF_AC;
        } else {
            DDCUDRAC.OTHER_AC = DDCUCCCY.TRF_CARD;
        }
        CEP.TRC(SCCGWA, DDCUDRAC.OTHER_AC);
        if (CICQACAC.O_DATA.O_ACR_DATA.O_FRM_APP_ACR.equalsIgnoreCase("DC")) {
            DDCUDRAC.OTH_TX_TOOL = CICQACAC.DATA.AGR_NO;
        }
        DDCUDRAC.CCY = DDCUCCCY.CCY;
        DDCUDRAC.CCY_TYPE = DDCUCCCY.CCY_TYPE;
        DDCUDRAC.PSWD = DDCUCCCY.PSWD;
        DDCUDRAC.CARD_NO = DDCUCCCY.CARD_NO;
        DDCUDRAC.PSBK_NO = DDCUCCCY.PSBK_NO;
        DDCUDRAC.CHQ_TYPE = DDCUCCCY.CHQ_TYPE;
        DDCUDRAC.CHQ_NO = DDCUCCCY.CHQ_NO;
        DDCUDRAC.ID_TYPE = DDCUCCCY.ID_TYPE;
        DDCUDRAC.ID_NO = DDCUCCCY.ID_NO;
        DDCUDRAC.PAY_TYPE = DDCUCCCY.PAY_TYPE;
        DDCUDRAC.PAY_SIGN_NO = DDCUCCCY.PAY_SIGN_NO;
        DDCUDRAC.GD_WITHDR_FLG = DDCUCCCY.GD_WITHDR_FLG;
        DDCUDRAC.REMARKS = DDCUCCCY.REMARK;
        DDCUDRAC.NARRATIVE = DDCUCCCY.NARRATIVE;
        DDCUDRAC.CHK_PSW_FLG = 'N';
        DDCUDRAC.CLEAR_FLG = 'Y';
        if (DDCUDRAC.TRT_CTLW == null) DDCUDRAC.TRT_CTLW = "";
        JIBS_tmp_int = DDCUDRAC.TRT_CTLW.length();
        for (int i=0;i<32-JIBS_tmp_int;i++) DDCUDRAC.TRT_CTLW += " ";
        DDCUDRAC.TRT_CTLW = DDCUDRAC.TRT_CTLW.substring(0, 6 - 1) + "1" + DDCUDRAC.TRT_CTLW.substring(6 + 1 - 1);
        DDCUDRAC.RLT_AC = DDCUCCCY.RLT_AC;
        DDCUDRAC.RLT_AC_NAME = DDCUCCCY.RLT_AC_NAME;
        DDCUDRAC.RLT_BANK = DDCUCCCY.RLT_BANK;
        DDCUDRAC.RLT_REF_NO = DDCUCCCY.RLT_REF_NO;
        DDCUDRAC.RLT_CCY = DDCUCCCY.RLT_CCY;
        CEP.TRC(SCCGWA, DDCUDRAC.RLT_AC);
        S000_CALL_DDZUDRAC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DDCUDRAC.TX_AMT);
        WS_CLS_BAL = DDCUDRAC.TX_AMT;
        CEP.TRC(SCCGWA, WS_CLS_BAL);
        DDCUCCCY.TOT_BAL = WS_CLS_BAL;
    }
    public void B060_TRF_CLS_AMT_PROC() throws IOException,SQLException,Exception {
        if ((DDCUCCCY.TRF_AC.trim().length() > 0 
            || DDCUCCCY.TRF_CARD.trim().length() > 0) 
            && (WS_CLS_BAL != 0)) {
            IBS.init(SCCGWA, DDCUCRAC);
            DDCUCRAC.TX_TYPE = 'T';
            DDCUCRAC.AC = DDCUCCCY.TRF_AC;
            DDCUCRAC.CARD_NO = DDCUCCCY.TRF_CARD;
            DDCUCRAC.CCY = DDCUCCCY.TRF_CCY;
            DDCUCRAC.CCY_TYPE = DDCUCCCY.TRF_CCY_TYPE;
            if (DDCUCCCY.AC.trim().length() > 0) {
                DDCUCRAC.OTHER_AC = DDCUCCCY.AC;
            } else {
                DDCUCRAC.OTHER_AC = DDCUCCCY.CARD_NO;
            }
            DDCUCRAC.OTH_TX_TOOL = DDCUCCCY.CARD_NO;
            DDCUCRAC.TX_MMO = K_CR_MMO;
            CEP.TRC(SCCGWA, WS_CLS_BAL);
            DDCUCRAC.TX_AMT = WS_CLS_BAL;
            DDCUCRAC.REMARKS = DDCUCCCY.REMARK;
            DDCUCRAC.NARRATIVE = DDCUCCCY.NARRATIVE;
