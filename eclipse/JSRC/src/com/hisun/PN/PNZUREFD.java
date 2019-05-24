package com.hisun.PN;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.AI.AICPQIA;
import com.hisun.AI.AICUUPIA;
import com.hisun.BP.BPCMSG_ERROR_MSG;
import com.hisun.BP.BPCPFHIS;
import com.hisun.BP.BPCPOEWA;
import com.hisun.BP.BPCPORUP_DATA_INFO;
import com.hisun.BP.BPCUBUSE;
import com.hisun.BP.BPCUSBOX;
import com.hisun.CI.CICCUST;
import com.hisun.CI.CICQACRI;
import com.hisun.DD.DDCUCRAC;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;

public class PNZUREFD {
    boolean pgmRtn = false;
    String CPN_U_BPZPFHIS = "BP-PROC-FHIS";
    String CPN_U_SCZHMPW = "SC-HM-PASSWORD";
    String K_CNTR_TYPE = "CACH";
    String K_EVENT_CODE = "DR      ";
    String K_BVCD_BP1 = "056";
    String K_OUTPUT_FMT = "PN009";
    String WS_ERR_MSG = " ";
    String WS_ERR_INFO = " ";
    char WS_TABLE_FLG = ' ';
    char WS_STS = ' ';
    double WS_AMT1 = 0;
    String WS_STR1 = " ";
    double WS_AMT2 = 0;
    String WS_STR2 = " ";
    int WS_ISS_BR_MSG = 0;
    String WS_ENCRY_NO = " ";
    String WS_BRAC = " ";
    PNZUREFD_WS_MAC_DA WS_MAC_DA = new PNZUREFD_WS_MAC_DA();
    short WS_ACNAME_FLG = 0;
    String WS_ACNAME_IN = " ";
    String WS_ACNAME_AC = " ";
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    PNCMSG_ERROR_MSG PNCMSG_ERROR_MSG = new PNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    BPCUBUSE BPCUBUSE = new BPCUBUSE();
    BPCPOEWA BPCPOEWA = new BPCPOEWA();
    DDCUCRAC DDCUCRAC = new DDCUCRAC();
    BPCUSBOX BPCUSBOX = new BPCUSBOX();
    BPCPFHIS BPCPFHIS = new BPCPFHIS();
    AICUUPIA AICUUPIA = new AICUUPIA();
    AICPQIA AICPQIA = new AICPQIA();
    SCCHMPW SCCHMPW = new SCCHMPW();
    CICQACRI CICQACRI = new CICQACRI();
    CICCUST CICCUST = new CICCUST();
    PNCOTCEL PNCOTCEL = new PNCOTCEL();
    PNRBCC PNRBCC = new PNRBCC();
    PNRDFPSW PNRDFPSW = new PNRDFPSW();
    SCCGWA SCCGWA;
    BPCPORUP_DATA_INFO BPCPORUP;
    PNCUREFD PNCUREFD;
    public void MP(SCCGWA SCCGWA, PNCUREFD PNCUREFD) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.PNCUREFD = PNCUREFD;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "PNZUREFD return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPCPORUP = (BPCPORUP_DATA_INFO) SCCGWA.COMM_AREA.TR_BR_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B201_INPUT_CHK_PROC();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            if (PNCUREFD.DATA.FUNC == 'P') {
                B206_READ_PNTDFPSW_PROC();
                if (pgmRtn) return;
            }
        }
        B203_PNT_EVT_PROC();
        if (pgmRtn) return;
        if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("AI") 
            && PNCUREFD.DATA.CLR_CHNL == '0') {
            B204_01_AI_PROC();
            if (pgmRtn) return;
        } else {
            B204_CRDR_PROC();
            if (pgmRtn) return;
        }
        B205_BCC_MST_PROC();
        if (pgmRtn) return;
    }
    public void B201_INPUT_CHK_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACRI);
        CEP.TRC(SCCGWA, PNCUREFD.DATA.STL_AC);
        CICQACRI.DATA.AGR_NO = PNCUREFD.DATA.STL_AC;
        CICQACRI.FUNC = 'A';
        S000_CALL_CIZQACRI();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICQACRI.O_DATA.O_FRM_APP);
        if (PNCUREFD.DATA.FUNC == 'P') {
            if (PNCUREFD.DATA.STL_FLG == 'T' 
                && PNCUREFD.DATA.CLR_CHNL == '0') {
                if (PNCUREFD.DATA.STL_AC.trim().length() == 0) {
                    IBS.CPY2CLS(SCCGWA, PNCMSG_ERROR_MSG.PN_STLAC_NOT_IPT, PNCUREFD.RC);
                    WS_ERR_INFO = "UREFD-STL-AC=" + PNCUREFD.DATA.STL_AC + ",UREFD-STL-FLG=" + PNCUREFD.DATA.STL_FLG;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
        } else {
            if (PNCUREFD.DATA.STL_FLG == 'T' 
                && PNCUREFD.DATA.CLR_CHNL == '0') {
                if (PNCUREFD.DATA.STL_AC.trim().length() == 0) {
                    IBS.CPY2CLS(SCCGWA, PNCMSG_ERROR_MSG.PN_STLAC_NOT_IPT, PNCUREFD.RC);
                    WS_ERR_INFO = "UREFD-STL-AC=" + PNCUREFD.DATA.STL_AC + ",UREFD-STL-FLG=" + PNCUREFD.DATA.STL_FLG + ",UREFD-CLR-CHNL=" + PNCUREFD.DATA.CLR_CHNL;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
        }
        IBS.init(SCCGWA, PNRBCC);
        PNRBCC.KEY.BILL_KND = PNCUREFD.DATA.KND;
        PNRBCC.KEY.BILL_NO = PNCUREFD.DATA.CC_NO;
        T000_READ_PNTBCC_UPD();
        if (pgmRtn) return;
        if (WS_TABLE_FLG == 'N') {
            IBS.CPY2CLS(SCCGWA, PNCMSG_ERROR_MSG.PN_BCC_NOT_FND, PNCUREFD.RC);
            WS_ERR_INFO = "UREFD-KND=" + PNCUREFD.DATA.KND + ",UREFD-CC-NO=" + PNCUREFD.DATA.CC_NO;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, PNRBCC.ISS_DT);
        CEP.TRC(SCCGWA, PNCUREFD.DATA.ISS_DATE);
        if (PNRBCC.ISS_DT != PNCUREFD.DATA.ISS_DATE) {
            IBS.CPY2CLS(SCCGWA, PNCMSG_ERROR_MSG.PN_ISS_DATE_ERR, PNCUREFD.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (PNRBCC.ISS_BR != SCCGWA.COMM_AREA.BR_DP.TR_BRANCH) {
            WS_ISS_BR_MSG = PNRBCC.ISS_BR;
            IBS.CPY2CLS(SCCGWA, PNCMSG_ERROR_MSG.PN_ISSBR_NOT_COMM, PNCUREFD.RC);
            WS_ERR_INFO = "BCC-ISS-BR=" + WS_ISS_BR_MSG + ",GWA-TR-BRANCH=" + SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, PNRBCC.C_T_FLG);
        CEP.TRC(SCCGWA, PNCUREFD.DATA.STL_FLG);
        if (PNRBCC.C_T_FLG == 'T' 
            && PNCUREFD.DATA.STL_FLG != 'T') {
            IBS.CPY2CLS(SCCGWA, PNCMSG_ERROR_MSG.PN_STL_FLG_MUST_T, PNCUREFD.RC);
            WS_ERR_INFO = "BCC-PAY-TYPE=" + PNRBCC.PAY_TYPE + ",UREFD-STL-FLG=" + PNCUREFD.DATA.STL_FLG;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (PNCUREFD.DATA.FUNC == 'P') {
            if (PNRBCC.AMT != PNCUREFD.DATA.ISS_AMT) {
                IBS.CPY2CLS(SCCGWA, PNCMSG_ERROR_MSG.PN_ISSAMT_NOT_COMM, PNCUREFD.RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (!CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("AI")) {
                WS_ACNAME_IN = PNRBCC.APP_ACNM;
                WS_ACNAME_AC = PNCUREFD.DATA.STL_NM;
