package com.hisun.PS;

import com.hisun.SC.*;
import com.hisun.BP.*;
import com.hisun.PN.*;
import com.hisun.AI.*;
import com.hisun.DD.*;
import com.hisun.CI.*;

import java.io.IOException;
import java.sql.SQLException;

public class PSZIBPJF {
    DBParm PNTDFPSW_RD;
    DBParm PNTBCC_RD;
    String CPN_U_DDZUCRAC = "DD-UNIT-DEP-PROC";
    String CPN_U_BPZUSBOX = "BP-U-SUB-CBOX";
    String CPN_P_AIZUUPIA = "AI-U-UPDATE-IA";
    String CPN_U_BPZPOEWA = "BP-P-WRT-ONL-EWA";
    String CPN_U_BPZPFHIS = "BP-PROC-FHIS";
    String K_CNTR_TYPE = "CACH";
    String K_EVENT_CODE = "DR      ";
    String CPN_U_SCZHMPW = "SC-HM-PASSWORD";
    String K_BVCD_BP1 = "056";
    String K_OUTPUT_FMT = "PN009";
    char WS_TABLE_FLG = ' ';
    String WS_ERR_MSG = " ";
    String WS_ERR_INFO = " ";
    PSZIBPJF_WS_MSGID WS_MSGID = new PSZIBPJF_WS_MSGID();
    PSZIBPJF_CP_PROD_CD CP_PROD_CD = new PSZIBPJF_CP_PROD_CD();
    PSZIBPJF_WS_MAC_DA WS_MAC_DA = new PSZIBPJF_WS_MAC_DA();
    PSZIBPJF_WS_DATE_ALL WS_DATE_ALL = new PSZIBPJF_WS_DATE_ALL();
    int WS_DATE = 0;
    short WS_HEAD_NO = 0;
    short WS_NO = 0;
    String WS_CC_NO_CHANGE = " ";
    short WS_CNT_NUM = 0;
    char WS_CI_TYP = ' ';
    String WS_ENCRY_NO = " ";
    char WS_PAY_MTH_FLG = ' ';
    char WS_APB_FLG = ' ';
    char WS_STS_CHANGE_FLG = ' ';
    SCCCLDT SCCCLDT = new SCCCLDT();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    PNCMSG_ERROR_MSG PNCMSG_ERROR_MSG = new PNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    BPCUSBOX BPCUSBOX = new BPCUSBOX();
    BPCUABOX BPCUABOX = new BPCUABOX();
    AICPIAEV AICPIAEV = new AICPIAEV();
    DDCUCRAC DDCUCRAC = new DDCUCRAC();
    DDCUDRAC DDCUDRAC = new DDCUDRAC();
    BPCPOEWA BPCPOEWA = new BPCPOEWA();
    AICUUPIA AICUUPIA = new AICUUPIA();
    SCCHMPW SCCHMPW = new SCCHMPW();
    AICPQIA AICPQIA = new AICPQIA();
    BPCPFHIS BPCPFHIS = new BPCPFHIS();
    CICQACRI CICQACRI = new CICQACRI();
    PNCOTCEL PNCOTCEL = new PNCOTCEL();
    PNRBCC PNRBCC = new PNRBCC();
    PNRDFPSW PNRDFPSW = new PNRDFPSW();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    BPCPORUP_DATA_INFO BPCPORUP;
    PSCIBPJF PSCIBPJF;
    public void MP(SCCGWA SCCGWA, PSCIBPJF PSCIBPJF) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.PSCIBPJF = PSCIBPJF;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "PSZIBPJF return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPCPORUP = (BPCPORUP_DATA_INFO) SCCGWA.COMM_AREA.TR_BR_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B102_PNT_COMM_CHK();
        B205_BIL_ENCRY_PROC();
        B206_PNT_EVT_PROC();
        B220_BCC_MST_PROC();
    }
    public void B102_PNT_COMM_CHK() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, PNRBCC);
        CEP.TRC(SCCGWA, PSCIBPJF.KND);
        CEP.TRC(SCCGWA, PSCIBPJF.CC_NO);
        PNRBCC.KEY.BILL_KND = PSCIBPJF.KND;
        PNRBCC.KEY.BILL_NO = PSCIBPJF.CC_NO;
        T000_READ_PNTBCC_UPD();
        if (WS_TABLE_FLG == 'N') {
            WS_ERR_MSG = PNCMSG_ERROR_MSG.PN_BCC_NOT_FND;
            S000_ERR_MSG_PROC();
        }
        if (PNRBCC.AMT != PSCIBPJF.ISS_AMT) {
            CEP.TRC(SCCGWA, "CHECK BCC-AMT=IBPJF-ISSAMT");
            CEP.TRC(SCCGWA, PNRBCC.AMT);
            WS_ERR_MSG = PNCMSG_ERROR_MSG.PN_ISSAMT_NOT_EQ;
            S000_ERR_MSG_PROC();
        }
        if (PNRBCC.ISS_DT != PSCIBPJF.ISS_DATE) {
            CEP.TRC(SCCGWA, "CHECK BCC-ISS-DT=IBPJF-ISS-DT");
            CEP.TRC(SCCGWA, PNRBCC.AMT);
            WS_ERR_MSG = PNCMSG_ERROR_MSG.PN_ISS_DATE_ERR;
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, PNRBCC.STS);
        if (PNRBCC.STS != '1' 
            && PNRBCC.STS != '2') {
            if (PNRBCC.STS == '3') {
                CEP.ERR(SCCGWA, PNCMSG_ERROR_MSG.PN_STS_LOSE);
            }
            if (PNRBCC.STS == '4') {
                CEP.ERR(SCCGWA, PNCMSG_ERROR_MSG.PN_STS_STOP);
            }
            if (PNRBCC.STS == '5') {
                CEP.ERR(SCCGWA, PNCMSG_ERROR_MSG.PN_STS_CHANGE);
            }
            if (PNRBCC.STS == '6') {
                CEP.ERR(SCCGWA, PNCMSG_ERROR_MSG.PN_STS_ROLL);
            }
            if (PNRBCC.STS == '7') {
                CEP.ERR(SCCGWA, PNCMSG_ERROR_MSG.PN_STS_REFUNDMENT);
            }
            if (PNRBCC.STS == '8') {
                CEP.ERR(SCCGWA, PNCMSG_ERROR_MSG.PN_STS_CANCEL);
            }
            if (PNRBCC.STS != '3' 
                && PNRBCC.STS != '4' 
                && PNRBCC.STS != '5' 
                && PNRBCC.STS != '6' 
                && PNRBCC.STS != '7' 
                && PNRBCC.STS != '8') {
                CEP.ERR(SCCGWA, PNCMSG_ERROR_MSG.PN_STS_ERR);
            }
        } else {
            if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
                if (PNRBCC.STS == '1') {
                    CEP.ERR(SCCGWA, PNCMSG_ERROR_MSG.PN_STS_ISS);
                }
            } else {
                if (PNRBCC.STS == '2') {
                    CEP.ERR(SCCGWA, PNCMSG_ERROR_MSG.PN_STS_PAY);
                }
            }
        }
        if (PNRBCC.ODUE_FLG == '1') {
            if (SCCGWA.COMM_AREA.BR_DP.TR_BRANCH != PNRBCC.ISS_BR) {
                WS_ERR_MSG = PNCMSG_ERROR_MSG.PN_CLRBR_NOT_COMM;
                S000_ERR_MSG_PROC();
            }
        }
    }
    public void B205_BIL_ENCRY_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, PNRDFPSW);
        PNRDFPSW.KEY.BILL_KND = "C00001";
        PNRDFPSW.KEY.BILL_NO = PSCIBPJF.CC_NO;
        T000_READ_PNTDFPSW();
        if (WS_TABLE_FLG == 'Y') {
            CEP.TRC(SCCGWA, PNRDFPSW.ENCRY_NO);
            WS_ENCRY_NO = PNRDFPSW.ENCRY_NO;
        } else {
            CEP.ERR(SCCGWA, PNCMSG_ERROR_MSG.PN_PNTDFPSW_NOT_FND);
        }
        if (!PSCIBPJF.ENCRY_NO.equalsIgnoreCase(WS_ENCRY_NO)) {
            WS_ERR_MSG = PNCMSG_ERROR_MSG.PN_ENCRY_NO_ERR;
            WS_ERR_INFO = "IBPJF-ENCRY-NO=" + PSCIBPJF.ENCRY_NO;
            S000_ERR_MSG_PROC();
        }
    }
    public void B206_PNT_EVT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPOEWA);
        BPCPOEWA.DATA.CNTR_TYPE = K_CNTR_TYPE;
        BPCPOEWA.DATA.EVENT_CODE = K_EVENT_CODE;
        BPCPOEWA.DATA.BR_OLD = PNRBCC.ISS_BR;
        BPCPOEWA.DATA.BR_NEW = PNRBCC.ISS_BR;
        BPCPOEWA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCPOEWA.DATA.CCY_INFO[1-1].CCY = PNRBCC.CCY;
        BPCPOEWA.DATA.AMT_INFO[1-1].AMT = PNRBCC.AMT;
        BPCPOEWA.DATA.PROD_CODE = PNRBCC.PRD_CD;
        BPCPOEWA.DATA.AC_NO = PNRBCC.KEY.BILL_NO;
        S000_CALL_BPZPOEWA();
    }
    public void B220_BCC_MST_PROC() throws IOException,SQLException,Exception {
        PNRBCC.LAST_STS = PNRBCC.STS;
        PNRBCC.STS = '2';
        PNRBCC.STL_TYPE = 'T';
        PNRBCC.LHD_AC = PSCIBPJF.LHD_AC;
        PNRBCC.LHD_ACNM = PSCIBPJF.LHD_NM;
        PNRBCC.STL_OPT = '1';
        PNRBCC.STL_CHNL = SCCGWA.COMM_AREA.CHNL;
        PNRBCC.CLR_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        PNRBCC.CLR_TLR = SCCGWA.COMM_AREA.TL_ID;
        PNRBCC.CLR_DATE = SCCGWA.COMM_AREA.AC_DATE;
        PNRBCC.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        PNRBCC.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        T000_REWRITE_PNTBCC();
    }
    public void S000_CALL_BPZPOEWA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_U_BPZPOEWA, BPCPOEWA);
        if (BPCPOEWA.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPOEWA.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void T000_READ_PNTDFPSW() throws IOException,SQLException,Exception {
        PNTDFPSW_RD = new DBParm();
        PNTDFPSW_RD.TableName = "PNTDFPSW";
        IBS.READ(SCCGWA, PNRDFPSW, PNTDFPSW_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TABLE_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TABLE_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "PNTDFPSW";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
        }
    }
    public void T000_READ_PNTBCC_UPD() throws IOException,SQLException,Exception {
        PNTBCC_RD = new DBParm();
        PNTBCC_RD.TableName = "PNTBCC";
        PNTBCC_RD.upd = true;
        IBS.READ(SCCGWA, PNRBCC, PNTBCC_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TABLE_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TABLE_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "PNTBCC";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
        }
    }
    public void T000_REWRITE_PNTBCC() throws IOException,SQLException,Exception {
        PNTBCC_RD = new DBParm();
        PNTBCC_RD.TableName = "PNTBCC";
        IBS.REWRITE(SCCGWA, PNRBCC, PNTBCC_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TABLE_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TABLE_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "PNTBCC";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "REWRITE";
            B_DB_EXCP();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_ERR_INFO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
