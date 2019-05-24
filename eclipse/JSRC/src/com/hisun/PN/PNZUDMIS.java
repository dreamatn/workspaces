package com.hisun.PN;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.BP.BPCFFTXI;
import com.hisun.BP.BPCGCFEE;
import com.hisun.BP.BPCGPFEE;
import com.hisun.BP.BPCMSG_ERROR_MSG;
import com.hisun.BP.BPCPNHIS;
import com.hisun.BP.BPCPORUP_DATA_INFO;
import com.hisun.BP.BPCTCALF;
import com.hisun.BP.BPCUBUSE;
import com.hisun.CI.CICACCU;
import com.hisun.CI.CICQACRI;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;

public class PNZUDMIS {
    boolean pgmRtn = false;
    String CPN_F_BPZFFTXI = "BP-F-F-TX-INFO";
    String CPN_F_BPZFCALF = "BP-F-F-CAL-FEE";
    String CPN_U_BPZPNHIS = "BP-REC-NHIS";
    String WS_ERR_MSG = " ";
    String WS_ERR_INFO = " ";
    char WS_TABLE_FLG = ' ';
    char WS_TABLE_GBCC_FLG = ' ';
    int WS_CNT = 0;
    int WS_ISS_BR = 0;
    int WS_AGT_BK_NO = 0;
    short WS_IDX = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    PNCMSG_ERROR_MSG PNCMSG_ERROR_MSG = new PNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCUBUSE BPCUBUSE = new BPCUBUSE();
    BPCFFTXI BPCFFTXI = new BPCFFTXI();
    BPCTCALF BPCTCALF = new BPCTCALF();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    CICACCU CICACCU = new CICACCU();
    CICQACRI CICQACRI = new CICQACRI();
    PNRDFT PNRDFT = new PNRDFT();
    PNRGBCC PNRGBCC = new PNRGBCC();
    SCCGWA SCCGWA;
    BPCPORUP_DATA_INFO BPCPORUP;
    PNCUDMIS PNCUDMIS;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCGCFEE BPCGCFEE;
    BPCGPFEE BPCGPFEE;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA, PNCUDMIS PNCUDMIS) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.PNCUDMIS = PNCUDMIS;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "PNZUDMIS return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPCPORUP = (BPCPORUP_DATA_INFO) SCCGWA.COMM_AREA.TR_BR_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        BPCGCFEE = new BPCGCFEE();
        IBS.init(SCCGWA, BPCGCFEE);
        IBS.CPY2CLS(SCCGWA, GWA_BP_AREA.FEE_AREA.FEE_DATA_PTR, BPCGCFEE);
        BPCGPFEE = (BPCGPFEE) GWA_BP_AREA.FEE_AREA.FEE_PARM_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B201_INPUT_CHK_PROC();
        if (pgmRtn) return;
        if (PNCUDMIS.DATA.FUNC == 'L') {
            B205_FEE_PROC();
            if (pgmRtn) return;
        }
        B202_DFT_MST_PROC();
        if (pgmRtn) return;
        B203_GBCC_MST_PROC();
        if (pgmRtn) return;
    }
    public void B201_INPUT_CHK_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, PNCUDMIS.DATA.FUNC);
        if (PNCUDMIS.DATA.FUNC == 'L') {
            if (PNCUDMIS.DATA.LOS_DATE == 0) {
                IBS.CPY2CLS(SCCGWA, PNCMSG_ERROR_MSG.PN_LOSDT_NOT_IPT, PNCUDMIS.RC);
                WS_ERR_INFO = "UDMIS-LOS-DATE=" + PNCUDMIS.DATA.LOS_DATE;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (PNCUDMIS.DATA.LOS_ADDR.trim().length() == 0) {
                IBS.CPY2CLS(SCCGWA, PNCMSG_ERROR_MSG.PN_LOSAD_NOT_IPT, PNCUDMIS.RC);
                WS_ERR_INFO = "UDMIS-LOS-ADDR=" + PNCUDMIS.DATA.LOS_ADDR;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (PNCUDMIS.DATA.APP_NM.trim().length() == 0) {
                IBS.CPY2CLS(SCCGWA, PNCMSG_ERROR_MSG.PN_APNM_MUST_IPT, PNCUDMIS.RC);
                WS_ERR_INFO = "UDMIS-APP-NM=" + PNCUDMIS.DATA.APP_NM;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (PNCUDMIS.DATA.APP_ADDR.trim().length() == 0) {
                IBS.CPY2CLS(SCCGWA, PNCMSG_ERROR_MSG.PN_APADR_NOT_IPT, PNCUDMIS.RC);
                WS_ERR_INFO = "UDMIS-APP-ADDR=" + PNCUDMIS.DATA.APP_ADDR;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (PNCUDMIS.DATA.APP_TEL.trim().length() == 0) {
                IBS.CPY2CLS(SCCGWA, PNCMSG_ERROR_MSG.PN_APTEL_NOT_IPT, PNCUDMIS.RC);
                WS_ERR_INFO = "UDMIS-APP-TEL=" + PNCUDMIS.DATA.APP_TEL;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (PNCUDMIS.DATA.ID_TYPE.trim().length() == 0) {
                IBS.CPY2CLS(SCCGWA, PNCMSG_ERROR_MSG.PN_IDTYP_NOT_IPT, PNCUDMIS.RC);
                WS_ERR_INFO = "UDMIS-ID-TYPE=" + PNCUDMIS.DATA.ID_TYPE;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (PNCUDMIS.DATA.ID_NO.trim().length() == 0) {
                IBS.CPY2CLS(SCCGWA, PNCMSG_ERROR_MSG.PN_IDNO_NOT_IPT, PNCUDMIS.RC);
                WS_ERR_INFO = "UDMIS-ID-NO=" + PNCUDMIS.DATA.ID_NO;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (PNCUDMIS.DATA.REASON.trim().length() == 0) {
                IBS.CPY2CLS(SCCGWA, PNCMSG_ERROR_MSG.PN_RSN_NOT_IPT, PNCUDMIS.RC);
                WS_ERR_INFO = "UDMIS-REASON=" + PNCUDMIS.DATA.REASON;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            CEP.TRC(SCCGWA, PNCUDMIS.DATA.DFT_CINO);
        }
        if (PNCUDMIS.DATA.FUNC == 'S') {
            if (PNCUDMIS.DATA.STP_DATE == 0) {
                IBS.CPY2CLS(SCCGWA, PNCMSG_ERROR_MSG.PN_STPDT_NOT_IPT, PNCUDMIS.RC);
                WS_ERR_INFO = "UDMIS-STP-DATE=" + PNCUDMIS.DATA.STP_DATE;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (PNCUDMIS.DATA.STP_RENO.trim().length() == 0) {
                IBS.CPY2CLS(SCCGWA, PNCMSG_ERROR_MSG.PN_STPRENO_MUST_INPT, PNCUDMIS.RC);
                WS_ERR_INFO = "UDMIS-STP-RENO=" + PNCUDMIS.DATA.STP_RENO;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (PNCUDMIS.DATA.STP_RSN.trim().length() == 0) {
                IBS.CPY2CLS(SCCGWA, PNCMSG_ERROR_MSG.PN_RSN_NOT_IPT, PNCUDMIS.RC);
                WS_ERR_INFO = "UDMIS-STP-RSN=" + PNCUDMIS.DATA.STP_RSN;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (PNCUDMIS.DATA.FUNC == 'R') {
            if (PNCUDMIS.DATA.RM_NM.trim().length() == 0) {
                IBS.CPY2CLS(SCCGWA, PNCMSG_ERROR_MSG.PN_RMNM_MUST_INPT, PNCUDMIS.RC);
                WS_ERR_INFO = "UDMIS-RM-NM=" + PNCUDMIS.DATA.RM_NM;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (PNCUDMIS.DATA.RM_ADDR.trim().length() == 0) {
                IBS.CPY2CLS(SCCGWA, PNCMSG_ERROR_MSG.PN_RMADDR_MUST_INPT, PNCUDMIS.RC);
                WS_ERR_INFO = "UDMIS-RM-ADDR=" + PNCUDMIS.DATA.RM_ADDR;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (PNCUDMIS.DATA.RM_TEL.trim().length() == 0) {
                IBS.CPY2CLS(SCCGWA, PNCMSG_ERROR_MSG.PN_RMTEL_MUST_INPT, PNCUDMIS.RC);
                WS_ERR_INFO = "UDMIS-RM-TEL=" + PNCUDMIS.DATA.RM_TEL;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (PNCUDMIS.DATA.RM_IDTY.trim().length() == 0) {
                IBS.CPY2CLS(SCCGWA, PNCMSG_ERROR_MSG.PN_IDTYP_NOT_IPT, PNCUDMIS.RC);
                WS_ERR_INFO = "UDMIS-RM-IDTY=" + PNCUDMIS.DATA.RM_IDTY;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (PNCUDMIS.DATA.RM_IDNO.trim().length() == 0) {
                IBS.CPY2CLS(SCCGWA, PNCMSG_ERROR_MSG.PN_IDNO_NOT_IPT, PNCUDMIS.RC);
                WS_ERR_INFO = "UDMIS-RM-IDNO=" + PNCUDMIS.DATA.RM_IDNO;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (PNCUDMIS.DATA.RM_SMR.trim().length() == 0) {
                IBS.CPY2CLS(SCCGWA, PNCMSG_ERROR_MSG.PN_RMSMR_MUST_INPT, PNCUDMIS.RC);
                WS_ERR_INFO = "UDMIS-RM-SMR=" + PNCUDMIS.DATA.RM_SMR;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        IBS.init(SCCGWA, PNRDFT);
        PNRDFT.KEY.BILL_KND = PNCUDMIS.DATA.KND;
        PNRDFT.KEY.BILL_NO = PNCUDMIS.DATA.DRFT_NO;
        CEP.TRC(SCCGWA, PNCUDMIS.DATA.KND);
        CEP.TRC(SCCGWA, PNCUDMIS.DATA.DRFT_NO);
        T000_READ_PNTDFT_UPD();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_TABLE_FLG);
        if (WS_TABLE_FLG == 'N') {
            IBS.CPY2CLS(SCCGWA, PNCMSG_ERROR_MSG.PN_DFT_REC_NOT_EXIST, PNCUDMIS.RC);
            WS_ERR_INFO = "UDMIS-KND =" + PNCUDMIS.DATA.KND + ",UDMIS-DRFT-NO =" + PNCUDMIS.DATA.DRFT_NO;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, PNRDFT.ISS_DT);
        CEP.TRC(SCCGWA, PNRDFT.ISS_AMT);
        CEP.TRC(SCCGWA, PNCUDMIS.DATA.ISS_DATE);
        CEP.TRC(SCCGWA, PNCUDMIS.DATA.ISS_AMT);
        if (PNRDFT.ISS_DT != PNCUDMIS.DATA.ISS_DATE) {
            CEP.TRC(SCCGWA, "CHECK DFT-ISS-DT=UDMIS-ISS-DT");
            IBS.CPY2CLS(SCCGWA, PNCMSG_ERROR_MSG.PN_ISS_DATE_ERR, PNCUDMIS.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (PNRDFT.ISS_AMT != PNCUDMIS.DATA.ISS_AMT) {
            CEP.TRC(SCCGWA, "CHECK DFT-ISS-AMT=UDMIS-ISS-AMT");
            IBS.CPY2CLS(SCCGWA, PNCMSG_ERROR_MSG.PN_ISSAMT_NOT_EQ, PNCUDMIS.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (PNRDFT.C_T_FLG == 'C') {
            if (PNRDFT.ISS_BR != SCCGWA.COMM_AREA.BR_DP.TR_BRANCH 
                && PNRDFT.AGT_BK_NO != SCCGWA.COMM_AREA.BR_DP.TR_BRANCH) {
                WS_ISS_BR = PNRDFT.ISS_BR;
                WS_AGT_BK_NO = (int) PNRDFT.AGT_BK_NO;
                IBS.CPY2CLS(SCCGWA, PNCMSG_ERROR_MSG.PN_ISSBR_NOT_COMM, PNCUDMIS.RC);
                WS_ERR_INFO = "DFT-AGT-BK-NO=" + WS_AGT_BK_NO + ",DFT-ISS-BR=" + WS_ISS_BR;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        } else {
            if (PNRDFT.ISS_BR != SCCGWA.COMM_AREA.BR_DP.TR_BRANCH) {
                WS_ISS_BR = PNRDFT.ISS_BR;
                IBS.CPY2CLS(SCCGWA, PNCMSG_ERROR_MSG.PN_ISSBR_NOT_COMM, PNCUDMIS.RC);
                WS_ERR_INFO = "DFT-ISS-BR=" + WS_ISS_BR;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (PNCUDMIS.DATA.FUNC == 'L') {
            if (PNRDFT.STS != '1' 
                && PNRDFT.STS != '3') {
                IBS.CPY2CLS(SCCGWA, PNCMSG_ERROR_MSG.PN_STS_NOT_NML, PNCUDMIS.RC);
                WS_ERR_INFO = "DFT-STS=" + PNRDFT.STS;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (PNCUDMIS.DATA.FUNC == 'S') {
            if (PNRDFT.STS != '3' 
                && PNRDFT.STS != '1') {
                IBS.CPY2CLS(SCCGWA, PNCMSG_ERROR_MSG.PN_STS_NOT_NML, PNCUDMIS.RC);
                WS_ERR_INFO = "DFT-STS=" + PNRDFT.STS;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (PNCUDMIS.DATA.FUNC == 'R') {
            if (PNRDFT.STS != '3' 
                && PNRDFT.STS != '4') {
                IBS.CPY2CLS(SCCGWA, PNCMSG_ERROR_MSG.PN_STS_NOT_NML, PNCUDMIS.RC);
                WS_ERR_INFO = "DFT-STS=" + PNRDFT.STS;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void B202_DFT_MST_PROC() throws IOException,SQLException,Exception {
        if (PNCUDMIS.DATA.FUNC == 'L') {
            PNRDFT.LAST_STS = PNRDFT.STS;
            PNRDFT.STS = '3';
        }
        if (PNCUDMIS.DATA.FUNC == 'S') {
            PNRDFT.LAST_STS = PNRDFT.STS;
            PNRDFT.STS = '4';
        }
        if (PNCUDMIS.DATA.FUNC == 'R') {
            PNRDFT.LAST_STS = PNRDFT.STS;
            PNRDFT.STS = '1';
        }
        PNRDFT.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        PNRDFT.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        T000_REWRITE_PNTDFT();
        if (pgmRtn) return;
        if (WS_TABLE_FLG == 'N') {
            IBS.CPY2CLS(SCCGWA, PNCMSG_ERROR_MSG.PN_DFT_REC_NOT_EXIST, PNCUDMIS.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B203_GBCC_MST_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, PNRGBCC);
        PNRGBCC.KEY.BILL_KND = PNCUDMIS.DATA.KND;
        PNRGBCC.KEY.BILL_NO = PNCUDMIS.DATA.DRFT_NO;
        T000_READ_PNTGBCC_UPD();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_TABLE_GBCC_FLG);
        if (PNCUDMIS.DATA.FUNC == 'R' 
            && WS_TABLE_GBCC_FLG == 'N') {
            IBS.CPY2CLS(SCCGWA, PNCMSG_ERROR_MSG.PN_NOTFND_GBCC, PNCUDMIS.RC);
            WS_ERR_INFO = "UDMIS-FUNC=" + PNCUDMIS.DATA.FUNC;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (PNCUDMIS.DATA.FUNC == 'L') {
            PNRGBCC.KEY.BILL_KND = PNCUDMIS.DATA.KND;
            PNRGBCC.KEY.BILL_NO = PNCUDMIS.DATA.DRFT_NO;
            PNRGBCC.MISS_DATE = SCCGWA.COMM_AREA.AC_DATE;
            PNRGBCC.APP_NAME = PNCUDMIS.DATA.APP_NM;
            PNRGBCC.APP_ID_TYPE = PNCUDMIS.DATA.ID_TYPE;
            PNRGBCC.APP_ID_NO = PNCUDMIS.DATA.ID_NO;
            PNRGBCC.APP_TEL = PNCUDMIS.DATA.APP_TEL;
            PNRGBCC.APP_ADDR = PNCUDMIS.DATA.APP_ADDR;
            PNRGBCC.LOSE_DATE = PNCUDMIS.DATA.LOS_DATE;
            PNRGBCC.LOSE_ADDR = PNCUDMIS.DATA.LOS_ADDR;
            PNRGBCC.LOSE_RSN = PNCUDMIS.DATA.REASON;
            PNRGBCC.PAY_DATE = PNCUDMIS.DATA.PAY_DATE;
            PNRGBCC.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            PNRGBCC.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
