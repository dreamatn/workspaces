package com.hisun.PN;

import com.hisun.BP.*;
import com.hisun.SC.*;
import com.hisun.DD.*;
import com.hisun.AI.*;
import com.hisun.CI.*;

import java.io.IOException;
import java.sql.SQLException;

public class PNZUBLHX {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    DBParm PNTDFT_RD;
    boolean pgmRtn = false;
    String CPN_U_BPZPFHIS = "BP-PROC-FHIS";
    String K_CNTR_TYPE = "BADR";
    String CPN_P_AIZUUPIA = "AI-U-UPDATE-IA";
    String K_EVENT_CODE = "BS";
    String K_BVCD_QGHP = "C00005";
    String K_BVCD_HDHP = "C00098";
    String K_OUTPUT_FMT = "PN009";
    String WS_ERR_MSG = " ";
    String WS_ERR_INFO = " ";
    char WS_TABLE_FLG = ' ';
    char WS_STS = ' ';
    int WS_ISS_BR = 0;
    String WS_BRAC = " ";
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    PNCMSG_ERROR_MSG PNCMSG_ERROR_MSG = new PNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    BPCPOEWA BPCPOEWA = new BPCPOEWA();
    DDCUCRAC DDCUCRAC = new DDCUCRAC();
    BPCUSBOX BPCUSBOX = new BPCUSBOX();
    BPCPFHIS BPCPFHIS = new BPCPFHIS();
    AICUUPIA AICUUPIA = new AICUUPIA();
    AICPQIA AICPQIA = new AICPQIA();
    CICQACRI CICQACRI = new CICQACRI();
    PNCOTCEL PNCOTCEL = new PNCOTCEL();
    PNRDFT PNRDFT = new PNRDFT();
    SCCGWA SCCGWA;
    BPCPORUP_DATA_INFO BPCPORUP;
    PNCUBLHX PNCUBLHX;
    public void MP(SCCGWA SCCGWA, PNCUBLHX PNCUBLHX) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.PNCUBLHX = PNCUBLHX;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "PNZUBLHX return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPCPORUP = (BPCPORUP_DATA_INFO) SCCGWA.COMM_AREA.TR_BR_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B201_INPUT_CHK_PROC();
        if (pgmRtn) return;
        B203_PNT_EVT_PROC();
        if (pgmRtn) return;
        B204_CR_PROC();
        if (pgmRtn) return;
        B202_TBL_MST_PROC();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            CEP.TRC(SCCGWA, "--CANCEL OUT--");
            B205_OUTPUT_PROC();
            if (pgmRtn) return;
        }
    }
    public void B201_INPUT_CHK_PROC() throws IOException,SQLException,Exception {
        if (PNCUBLHX.DATA.STL_FLG == 'T' 
            && PNCUBLHX.DATA.STL_AC.trim().length() == 0 
            && PNCUBLHX.DATA.STL_CHNL == '0') {
            IBS.CPY2CLS(SCCGWA, PNCMSG_ERROR_MSG.PN_STLAC_NOT_IPT, PNCUBLHX.RC);
            WS_ERR_INFO = "UBLHX-STL-FLG=" + PNCUBLHX.DATA.STL_FLG + ",UBLHX-STL-AC=" + PNCUBLHX.DATA.STL_AC;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, PNRDFT);
        PNRDFT.KEY.BILL_KND = PNCUBLHX.DATA.KND;
        CEP.TRC(SCCGWA, PNCUBLHX.DATA.KND);
        PNRDFT.KEY.BILL_NO = PNCUBLHX.DATA.DRFT_NO;
        CEP.TRC(SCCGWA, PNCUBLHX.DATA.DRFT_NO);
        T000_READ_PNTDFT_UPD();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, PNRDFT.BAL_AMT);
        if (WS_TABLE_FLG == 'N') {
            IBS.CPY2CLS(SCCGWA, PNCMSG_ERROR_MSG.PN_PNT_NOT_FND, PNCUBLHX.RC);
            WS_ERR_INFO = "UBLHX-KND =" + PNCUBLHX.DATA.KND + ",UBLHX-DRFT-NO=" + PNCUBLHX.DATA.DRFT_NO;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (PNRDFT.C_T_FLG == 'T' 
            && PNCUBLHX.DATA.STL_FLG != 'T') {
            IBS.CPY2CLS(SCCGWA, PNCMSG_ERROR_MSG.PN_STL_FLG_MUST_T, PNCUBLHX.RC);
            WS_ERR_INFO = "UBLHX-STL-FLG=" + PNCUBLHX.DATA.STL_FLG + ",UBLHX-STL-AC=" + PNCUBLHX.DATA.STL_AC;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            if (PNRDFT.STS != '6') {
                IBS.CPY2CLS(SCCGWA, PNCMSG_ERROR_MSG.PN_STS_NOT_NML, PNCUBLHX.RC);
                WS_ERR_INFO = "DFT-STS=" + PNRDFT.STS;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (PNRDFT.UPDTBL_DATE != SCCGWA.COMM_AREA.AC_DATE) {
                IBS.CPY2CLS(SCCGWA, PNCMSG_ERROR_MSG.PN_NOT_ALLOW_CANCEL, PNCUBLHX.RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        } else {
            CEP.TRC(SCCGWA, "NCB1016001");
            CEP.TRC(SCCGWA, PNRDFT.STS);
            if (!(PNRDFT.STS == '2' 
                || PNRDFT.STS == '8')) {
                IBS.CPY2CLS(SCCGWA, PNCMSG_ERROR_MSG.PN_STS_NOT_NML, PNCUBLHX.RC);
                WS_ERR_INFO = "DFT-STS=" + PNRDFT.STS;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (PNRDFT.ISS_BR != SCCGWA.COMM_AREA.BR_DP.TR_BRANCH) {
            IBS.CPY2CLS(SCCGWA, PNCMSG_ERROR_MSG.PN_CLRBR_NOT_COMM, PNCUBLHX.RC);
            WS_ISS_BR = PNRDFT.ISS_BR;
            WS_ERR_INFO = "DFT-ISS-BR=" + WS_ISS_BR + ",GWA-TR-BRANCH" + SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B202_TBL_MST_PROC() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            PNRDFT.LAST_STS = PNRDFT.STS;
            PNRDFT.STS = '6';
            PNRDFT.CR_AC = PNCUBLHX.DATA.STL_AC;
        } else {
            WS_STS = PNRDFT.STS;
            PNRDFT.STS = PNRDFT.LAST_STS;
            PNRDFT.LAST_STS = WS_STS;
            PNRDFT.CR_AC = " ";
        }
        PNRDFT.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        PNRDFT.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        T000_REWRITE_PNTDFT();
        if (pgmRtn) return;
        if (WS_TABLE_FLG == 'N') {
            IBS.CPY2CLS(SCCGWA, PNCMSG_ERROR_MSG.PN_PNT_NOT_FND, PNCUBLHX.RC);
            WS_ERR_INFO = "UBLHX-KND =" + PNCUBLHX.DATA.KND + ",UBLHX-DRFT-NO=" + PNCUBLHX.DATA.DRFT_NO;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B203_PNT_EVT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPOEWA);
        BPCPOEWA.DATA.CNTR_TYPE = K_CNTR_TYPE;
        BPCPOEWA.DATA.EVENT_CODE = K_EVENT_CODE;
        BPCPOEWA.DATA.BR_OLD = PNRDFT.ISS_BR;
        BPCPOEWA.DATA.BR_NEW = PNRDFT.ISS_BR;
        BPCPOEWA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCPOEWA.DATA.CCY_INFO[3-1].CCY = PNRDFT.ISS_CCY;
        BPCPOEWA.DATA.CCY_INFO[1-1].CCY = PNRDFT.ISS_CCY;
        BPCPOEWA.DATA.AMT_INFO[3-1].AMT = PNRDFT.BAL_AMT;
        BPCPOEWA.DATA.PROD_CODE = PNRDFT.PROD_CD;
        BPCPOEWA.DATA.RVS_NO = PNRDFT.CREV_NO;
        BPCPOEWA.DATA.RVS_SEQ = PNRDFT.RVS_SEQ;
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.EVENT_CODE);
        S000_CALL_BPZPOEWA();
        if (pgmRtn) return;
    }
    public void B204_CR_PROC() throws IOException,SQLException,Exception {
        if (PNCUBLHX.DATA.STL_FLG == 'T' 
            && PNCUBLHX.DATA.STL_CHNL == '0') {
            CEP.TRC(SCCGWA, "-----11111----");
            IBS.init(SCCGWA, DDCUCRAC);
            DDCUCRAC.AC = PNCUBLHX.DATA.STL_AC;
            DDCUCRAC.CCY = PNRDFT.ISS_CCY;
            DDCUCRAC.TX_AMT = PNRDFT.BAL_AMT;
            DDCUCRAC.CCY_TYPE = '1';
            DDCUCRAC.VAL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DDCUCRAC.OTHER_AC = " ";
            DDCUCRAC.OTHER_CCY = " ";
            DDCUCRAC.TX_TYPE = 'T';
            DDCUCRAC.BANK_CR_FLG = 'Y';
            DDCUCRAC.TX_MMO = "A320";
            S000_CALL_DDZUCRAC();
            if (pgmRtn) return;
        }
        if (PNCUBLHX.DATA.STL_FLG == 'C' 
            && PNCUBLHX.DATA.STL_CHNL == '0') {
            CEP.TRC(SCCGWA, "-----22222----");
            IBS.init(SCCGWA, BPCUSBOX);
            BPCUSBOX.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            BPCUSBOX.TLR = SCCGWA.COMM_AREA.TL_ID;
            BPCUSBOX.CCY = PNRDFT.ISS_CCY;
            BPCUSBOX.AMT = PNRDFT.BAL_AMT;
            BPCUSBOX.OPP_AC = PNCUBLHX.DATA.STL_AC;
            BPCUSBOX.OPP_ACNM = PNCUBLHX.DATA.STL_NM;
            BPCUSBOX.CI_NO = " ";
            BPCUSBOX.ID_TYP = " ";
            BPCUSBOX.IDNO = " ";
            BPCUSBOX.AGT_IDTYP = " ";
            BPCUSBOX.AGT_IDNO = " ";
            BPCUSBOX.AGT_NAME = " ";
            S000_CALL_BPZUSBOX();
            if (pgmRtn) return;
        }
        if (PNCUBLHX.DATA.STL_CHNL == '1' 
            && PNCUBLHX.DATA.STL_FLG == 'T') {
            CEP.TRC(SCCGWA, "-----33333----");
            IBS.init(SCCGWA, AICPQIA);
            AICPQIA.CD.AC_TYP = "3";
            AICPQIA.CD.BUSI_KND = "BPGZ";
            AICPQIA.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            AICPQIA.CCY = PNRDFT.ISS_CCY;
            AICPQIA.SIGN = 'C';
            S000_CALL_AIZPQIA();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, AICPQIA.AC);
            IBS.init(SCCGWA, AICUUPIA);
            AICUUPIA.DATA.AC_NO = AICPQIA.AC;
            AICUUPIA.DATA.CCY = PNRDFT.ISS_CCY;
            AICUUPIA.DATA.AMT = PNRDFT.BAL_AMT;
            AICUUPIA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
            AICUUPIA.DATA.EVENT_CODE = "CR";
            AICUUPIA.DATA.PAY_MAN = PNCUBLHX.DATA.STL_NM;
            AICUUPIA.DATA.THEIR_AC = PNCUBLHX.DATA.STL_AC;
            CEP.TRC(SCCGWA, AICUUPIA.DATA.THEIR_AC);
            S000_CALL_AIZUUPIA();
            if (pgmRtn) return;
        }
    }
    public void B205_OUTPUT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, PNCOTCEL);
        PNCOTCEL.KND = PNCUBLHX.DATA.KND.charAt(0);
        PNCOTCEL.CC_NO = PNCUBLHX.DATA.DRFT_NO;
        PNCOTCEL.STS = PNRDFT.STS;
        CEP.TRC(SCCGWA, PNCOTCEL.KND);
        CEP.TRC(SCCGWA, PNCOTCEL.CC_NO);
        CEP.TRC(SCCGWA, PNCOTCEL.STS);
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = PNCOTCEL;
        SCCFMT.DATA_LEN = 18;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B260_BP_HISTORY() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPFHIS);
        BPCPFHIS.DATA.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPFHIS.DATA.AC_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPCPFHIS.DATA.VCHNO = SCCGWA.COMM_AREA.VCH_NO;
        BPCPFHIS.DATA.AC = PNRDFT.KEY.BILL_NO;
        BPCPFHIS.DATA.ACO_AC = PNRDFT.KEY.BILL_NO;
        BPCPFHIS.DATA.TX_TOOL = PNRDFT.KEY.BILL_NO;
        BPCPFHIS.DATA.REF_NO = PNRDFT.KEY.BILL_NO;
        BPCPFHIS.DATA.SUMUP_FLG = '5';
        BPCPFHIS.DATA.TX_DRCR_FLG = 'D';
        BPCPFHIS.DATA.PRINT_IND = 'Y';
        if (PNCUBLHX.DATA.KND.equalsIgnoreCase("C00005")) {
            BPCPFHIS.DATA.BV_CODE = K_BVCD_QGHP;
        } else {
            BPCPFHIS.DATA.BV_CODE = K_BVCD_HDHP;
        }
        if (PNCUBLHX.DATA.DRFT_NO == null) PNCUBLHX.DATA.DRFT_NO = "";
        JIBS_tmp_int = PNCUBLHX.DATA.DRFT_NO.length();
        for (int i=0;i<30-JIBS_tmp_int;i++) PNCUBLHX.DATA.DRFT_NO += " ";
        BPCPFHIS.DATA.HEAD_NO = PNCUBLHX.DATA.DRFT_NO.substring(0, 8);
        if (PNCUBLHX.DATA.DRFT_NO == null) PNCUBLHX.DATA.DRFT_NO = "";
        JIBS_tmp_int = PNCUBLHX.DATA.DRFT_NO.length();
        for (int i=0;i<30-JIBS_tmp_int;i++) PNCUBLHX.DATA.DRFT_NO += " ";
        BPCPFHIS.DATA.BV_NO = PNCUBLHX.DATA.DRFT_NO.substring(9 - 1, 9 + 8 - 1);
        BPCPFHIS.DATA.TX_CCY = PNRDFT.ISS_CCY;
        BPCPFHIS.DATA.TX_CCY_TYP = '1';
        BPCPFHIS.DATA.TX_TYPE = PNRDFT.C_T_FLG;
        BPCPFHIS.DATA.TX_AMT = PNRDFT.BAL_AMT;
        BPCPFHIS.DATA.TX_MMO = "A320";
        BPCPFHIS.DATA.PROD_CD = PNRDFT.PROD_CD;
        BPCPFHIS.DATA.CI_NO = PNCUBLHX.DATA.CI_NO;
        BPCPFHIS.DATA.OTH_AC = PNCUBLHX.DATA.STL_AC;
        S000_CALL_BPZPFHIS();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZPFHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_U_BPZPFHIS, BPCPFHIS);
        if (BPCPFHIS.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPFHIS.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], PNCUBLHX.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_AIZPQIA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-P-INQ-IA", AICPQIA);
        CEP.TRC(SCCGWA, AICPQIA.AC);
        if (AICPQIA.RC.RC_CODE != 0 
            || AICPQIA.AC.trim().length() == 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, AICPQIA.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], PNCUBLHX.RC);
            WS_ERR_INFO = "PQIA-AC =" + AICPQIA.AC;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_AIZUUPIA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_AIZUUPIA, AICUUPIA);
        CEP.TRC(SCCGWA, AICUUPIA.RC);
        if (AICUUPIA.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, AICUUPIA.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], PNCUBLHX.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
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
        if (BPCPOEWA.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPOEWA.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], PNCUBLHX.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DDZUCRAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-UNIT-DEP-PROC", DDCUCRAC);
    }
    public void S000_CALL_BPZUSBOX() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-U-SUB-CBOX", BPCUSBOX);
    }
    public void S000_CALL_CIZQACRI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACR-INF", CICQACRI);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, PNCUBLHX.RC);
        CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        Z_RET();
        if (pgmRtn) return;
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (PNCUBLHX.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, PNCUBLHX);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
