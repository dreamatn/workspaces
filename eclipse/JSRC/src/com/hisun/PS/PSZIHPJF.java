package com.hisun.PS;

import com.hisun.BP.*;
import com.hisun.PN.*;
import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class PSZIHPJF {
    DBParm PNTDFT_RD;
    DBParm PNTDFPSW_RD;
    boolean pgmRtn = false;
    char K_ERROR = 'E';
    String K_CNTR_TYPE = "BADR";
    String K_EVENT_CODE = "DR      ";
    String CPN_U_SCZHMPW = "SC-HM-PASSWORD";
    String K_BVCD_QGHP = "C00005";
    String K_BVCD_HDHP = "C00005";
    PSZIHPJF_WS_MSGID WS_MSGID = new PSZIHPJF_WS_MSGID();
    String WS_ERR_MSG = " ";
    String WS_ERR_INFO = " ";
    short WS_FLD_NO = 0;
    int WS_ISS_BR = 0;
    short WS_ENCRY_LENGTH = 0;
    PSZIHPJF_WS_ERR_MSG_ID WS_ERR_MSG_ID = new PSZIHPJF_WS_ERR_MSG_ID();
    String WS_ENCRY_NO = " ";
    String WS_ENCRY_NO_LENTH = " ";
    PSZIHPJF_WS_MAC_DA_HDSS WS_MAC_DA_HDSS = new PSZIHPJF_WS_MAC_DA_HDSS();
    PSZIHPJF_WS_MAC_DA_IN WS_MAC_DA_IN = new PSZIHPJF_WS_MAC_DA_IN();
    PSZIHPJF_WS_MAC_DA WS_MAC_DA = new PSZIHPJF_WS_MAC_DA();
    double WS_AMT = 0;
    String WS_STR = " ";
    double WS_STL_AMT = 0;
    String WS_AMT2 = " ";
    double WS_BAL_AMT = 0;
    String WS_AMT3 = " ";
    String WS_BRAC = " ";
    double WS_ALL_AMT = 0;
    char WS_TABLE_FLG = ' ';
    char WS_DFPSW_FLG = ' ';
    char WS_PAY_FLG = ' ';
    char WS_CDDAT_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    PNCMSG_ERROR_MSG PNCMSG_ERROR_MSG = new PNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    BPCPOEWA BPCPOEWA = new BPCPOEWA();
    PNCOTCEL PNCOTCEL = new PNCOTCEL();
    PNRDFT PNRDFT = new PNRDFT();
    PNRDFPSW PNRDFPSW = new PNRDFPSW();
    SCCGWA SCCGWA;
    PSCIHPJF PSCIHPJF;
    public void MP(SCCGWA SCCGWA, PSCIHPJF PSCIHPJF) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.PSCIHPJF = PSCIHPJF;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "PSZIHPJF return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_INPUT_CHK_PROC();
        if (pgmRtn) return;
        B200_DRAFT_PAY_PROC();
        if (pgmRtn) return;
    }
    public void B100_INPUT_CHK_PROC() throws IOException,SQLException,Exception {
        if (PSCIHPJF.STL_AMT == 0) {
            CEP.ERR(SCCGWA, PNCMSG_ERROR_MSG.PN_DPAY_PAY_MONEY_NULL);
        }
        if (PSCIHPJF.BILL_NO.trim().length() == 0) {
            CEP.ERR(SCCGWA, PNCMSG_ERROR_MSG.PN_DRFT_NO_NULL);
        }
        if (PSCIHPJF.BILL_KND.trim().length() == 0) {
            CEP.ERR(SCCGWA, PNCMSG_ERROR_MSG.PN_KND_MUST_IPT);
        }
        if (PSCIHPJF.ENCRY_NO.trim().length() == 0) {
            CEP.ERR(SCCGWA, PNCMSG_ERROR_MSG.PN_ENCRY_NOT_IPT);
        }
        if (PSCIHPJF.STL_FLG == ' ') {
            CEP.ERR(SCCGWA, PNCMSG_ERROR_MSG.PN_STLFLG_NOT_IPT);
        }
        if (PSCIHPJF.CLR_CHNL == ' ') {
            CEP.ERR(SCCGWA, PNCMSG_ERROR_MSG.PN_CLR_CHNL_IPT);
        }
        if (PSCIHPJF.CLR_CHNL == '1' 
            && PSCIHPJF.STL_FLG == 'C') {
            WS_ERR_MSG = PNCMSG_ERROR_MSG.PN_STLFLG_MUST_T;
            WS_ERR_INFO = "IHPJF-CLR-CHNL=" + PSCIHPJF.CLR_CHNL + ",IHPJF-STL-FLG=" + PSCIHPJF.STL_FLG;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B200_DRAFT_PAY_PROC() throws IOException,SQLException,Exception {
        B210_CHECK_INPUT_COMM();
        if (pgmRtn) return;
        B250_CHECK_ENCRY_NO();
        if (pgmRtn) return;
        B230_POST_CREDIT();
        if (pgmRtn) return;
        B240_DFT_MST_PROC();
        if (pgmRtn) return;
    }
    public void B210_CHECK_INPUT_COMM() throws IOException,SQLException,Exception {
        PNRDFT.KEY.BILL_KND = PSCIHPJF.BILL_KND;
        PNRDFT.KEY.BILL_NO = PSCIHPJF.BILL_NO;
        T000_READ_PNTDFT_UPD();
        if (pgmRtn) return;
        if (WS_TABLE_FLG == 'N') {
            WS_ERR_MSG = PNCMSG_ERROR_MSG.PN_PNT_NOT_FND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, PNRDFT.ISS_AMT);
        CEP.TRC(SCCGWA, PSCIHPJF.ISS_AMT);
        if (PSCIHPJF.STL_AMT > PNRDFT.ISS_AMT) {
            WS_AMT = PNRDFT.ISS_AMT;
            WS_ERR_MSG = PNCMSG_ERROR_MSG.PN_STLAMT_GR_ISSAMT;
            WS_ERR_INFO = "IHPJF-STL-AMT= " + WS_STR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        WS_ALL_AMT = PSCIHPJF.STL_AMT + PSCIHPJF.BAL_AMT;
        CEP.TRC(SCCGWA, WS_ALL_AMT);
        if (WS_ALL_AMT != PNRDFT.ISS_AMT) {
            WS_STL_AMT = PSCIHPJF.STL_AMT;
            WS_BAL_AMT = PSCIHPJF.BAL_AMT;
            WS_AMT = PNRDFT.ISS_AMT;
            WS_ERR_MSG = PNCMSG_ERROR_MSG.PN_BALAMT_NOT_COMM;
            WS_ERR_INFO = "IHPJF-STL-AMT=" + WS_AMT2 + ",IHPJF-BAL-AMT=" + WS_AMT3 + ",DFT-ISS-AMT=" + WS_STR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, PNRDFT.STS);
        if (PNRDFT.STS != '1' 
            && PNRDFT.STS != '2' 
            && PNRDFT.STS != '6') {
            if (PNRDFT.STS == '3') {
                CEP.ERR(SCCGWA, PNCMSG_ERROR_MSG.PN_STS_LOSE);
            }
            if (PNRDFT.STS == '4') {
                CEP.ERR(SCCGWA, PNCMSG_ERROR_MSG.PN_STS_STOP);
            }
            if (PNRDFT.STS == '5') {
                CEP.ERR(SCCGWA, PNCMSG_ERROR_MSG.PN_STS_CHANGE);
            }
            if (PNRDFT.STS == '7') {
                CEP.ERR(SCCGWA, PNCMSG_ERROR_MSG.PN_STS_ROLL);
            }
            if (PNRDFT.STS == '8') {
                CEP.ERR(SCCGWA, PNCMSG_ERROR_MSG.PN_STS_REFUNDMENT);
            }
            if (PNRDFT.STS == '9') {
                CEP.ERR(SCCGWA, PNCMSG_ERROR_MSG.PN_STS_CANCEL);
            }
            if (PNRDFT.STS != '3' 
                && PNRDFT.STS != '4' 
                && PNRDFT.STS != '5' 
                && PNRDFT.STS != '7' 
                && PNRDFT.STS != '8' 
                && PNRDFT.STS != '9') {
                CEP.ERR(SCCGWA, PNCMSG_ERROR_MSG.PN_STS_ERR);
            }
        } else {
            if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
                if (PNRDFT.STS == '1') {
                    CEP.ERR(SCCGWA, PNCMSG_ERROR_MSG.PN_STS_ISS);
                }
                CEP.TRC(SCCGWA, PNRDFT.LAST_STS);
                if (PNRDFT.STS == '6' 
                    && PNRDFT.LAST_STS != '1') {
                    CEP.ERR(SCCGWA, PNCMSG_ERROR_MSG.PN_STS_CLR_LAST_NOT_ISS);
                }
            } else {
                if (PNRDFT.STS == '2') {
                    CEP.ERR(SCCGWA, PNCMSG_ERROR_MSG.PN_STS_PAY);
                }
                if (PNRDFT.STS == '6') {
                    CEP.ERR(SCCGWA, PNCMSG_ERROR_MSG.PN_STS_CLR);
                }
            }
        }
        if (PNRDFT.C_T_FLG == 'T' 
            && PSCIHPJF.STL_FLG == 'C') {
            WS_ERR_MSG = PNCMSG_ERROR_MSG.PN_STLFLG_MUST_T;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (PNRDFT.ODUE_FLG == '1' 
            && SCCGWA.COMM_AREA.BR_DP.TR_BRANCH != PNRDFT.ISS_BR) {
            WS_ERR_MSG = PNCMSG_ERROR_MSG.PN_CLRBR_NOT_COMM;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B230_POST_CREDIT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPOEWA);
        BPCPOEWA.DATA.CNTR_TYPE = K_CNTR_TYPE;
        BPCPOEWA.DATA.EVENT_CODE = K_EVENT_CODE;
        CEP.TRC(SCCGWA, PNRDFT.ISS_BR);
        BPCPOEWA.DATA.BR_OLD = PNRDFT.ISS_BR;
        BPCPOEWA.DATA.BR_NEW = PNRDFT.ISS_BR;
        BPCPOEWA.DATA.BR_NEW = PNRDFT.ISS_BR;
        BPCPOEWA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCPOEWA.DATA.CCY_INFO[1-1].CCY = PNRDFT.ISS_CCY;
        BPCPOEWA.DATA.AMT_INFO[1-1].AMT = PNRDFT.ISS_AMT;
        BPCPOEWA.DATA.PROD_CODE = PNRDFT.PROD_CD;
        BPCPOEWA.DATA.AC_NO = PNRDFT.KEY.BILL_NO;
        S000_CALL_BPZPOEWA();
        if (pgmRtn) return;
    }
    public void B240_DFT_MST_PROC() throws IOException,SQLException,Exception {
        PNRDFT.LAST_STS = PNRDFT.STS;
        if (PSCIHPJF.BAL_AMT > 0) {
            if (PNRDFT.PAY_TYPE == 'C') {
                PNRDFT.STS = '2';
            }
            if (PNRDFT.PAY_TYPE == 'T') {
                if (WS_PAY_FLG == 'Y') {
                    PNRDFT.STS = '2';
                } else {
                    PNRDFT.STS = '6';
                    PNRDFT.CR_AC = PSCIHPJF.LHD_AC;
                }
            }
        } else {
            PNRDFT.STS = '6';
            PNRDFT.STL_PAY = PSCIHPJF.STL_FLG;
            PNRDFT.STL_OPT = PSCIHPJF.CLR_CHNL;
            CEP.TRC(SCCGWA, PSCIHPJF.CLR_CHNL);
            PNRDFT.LHD_AC = PSCIHPJF.LHD_AC;
            PNRDFT.STL_AMT = PSCIHPJF.STL_AMT;
            PNRDFT.BAL_AMT = PSCIHPJF.BAL_AMT;
            PNRDFT.LHD_NAME = PSCIHPJF.LHD_NM;
            PNRDFT.STL_CHNL = SCCGWA.COMM_AREA.CHNL;
            PNRDFT.CLR_DATE = SCCGWA.COMM_AREA.AC_DATE;
            PNRDFT.CLR_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            PNRDFT.CLR_TLR = SCCGWA.COMM_AREA.TL_ID;
        }
        PNRDFT.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        PNRDFT.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        CEP.TRC(SCCGWA, PNRDFT.CREV_NO);
        CEP.TRC(SCCGWA, PNRDFT.RVS_SEQ);
        T000_REWRITE_PNTDFT();
        if (pgmRtn) return;
    }
    public void B250_CHECK_ENCRY_NO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, PNRDFPSW);
        PNRDFPSW.KEY.BILL_KND = PSCIHPJF.BILL_KND;
        PNRDFPSW.KEY.BILL_NO = PSCIHPJF.BILL_NO;
        CEP.TRC(SCCGWA, PNRDFPSW.KEY.BILL_NO);
        CEP.TRC(SCCGWA, PNRDFPSW.KEY.BILL_KND);
        T000_READ_PNTDFPSW();
        if (pgmRtn) return;
        if (WS_DFPSW_FLG == 'Y') {
            CEP.TRC(SCCGWA, PNRDFPSW.ENCRY_NO);
            if (!PSCIHPJF.ENCRY_NO.equalsIgnoreCase(PNRDFPSW.ENCRY_NO)) {
                CEP.ERR(SCCGWA, PNCMSG_ERROR_MSG.PN_ENCRY_NO_ERR);
            }
        } else {
            CEP.ERR(SCCGWA, PNCMSG_ERROR_MSG.PN_PNTDFPSW_NOT_FND);
        }
    }
    public void T000_READ_PNTDFT_UPD() throws IOException,SQLException,Exception {
        PNTDFT_RD = new DBParm();
        PNTDFT_RD.TableName = "PNTDFT";
        PNTDFT_RD.upd = true;
        IBS.READ(SCCGWA, PNRDFT, PNTDFT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TABLE_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TABLE_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "PNTDFT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_PNTDFPSW() throws IOException,SQLException,Exception {
        null.col = "ENCRY_NO";
        PNTDFPSW_RD = new DBParm();
        PNTDFPSW_RD.TableName = "PNTDFPSW";
        IBS.READ(SCCGWA, PNRDFPSW, PNTDFPSW_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_DFPSW_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_DFPSW_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "PNTDFPSW";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_PNTDFT() throws IOException,SQLException,Exception {
        PNTDFT_RD = new DBParm();
        PNTDFT_RD.TableName = "PNTDFT";
        IBS.REWRITE(SCCGWA, PNRDFT, PNTDFT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TABLE_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TABLE_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "PNTDFT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "REWRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPOEWA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-WRT-ONL-EWA", BPCPOEWA);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_ERR_INFO);
        Z_RET();
        if (pgmRtn) return;
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
