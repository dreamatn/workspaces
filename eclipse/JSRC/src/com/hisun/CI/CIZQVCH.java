package com.hisun.CI;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.BP.BPCPRMR;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;

public class CIZQVCH {
    boolean pgmRtn = false;
    String K_OUTPUT_FMT_9 = "CI812";
    int K_MAX_ROW = 20;
    int K_MAX_COL = 99;
    int K_COL_CNT = 6;
    String WS_ERR_MSG = " ";
    int WS_VCH_CNT = 0;
    String WS_CI_CNM = " ";
    String WS_CI_ENM = " ";
    char WS_ACR_FLG = ' ';
    char WS_JRL_FLG = ' ';
    char WS_BAS_FLG = ' ';
    char WS_FLG = ' ';
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    CIRACR CIRACR = new CIRACR();
    CIRJRL CIRJRL = new CIRJRL();
    CIRBAS CIRBAS = new CIRBAS();
    CIRNAM CIRNAM = new CIRNAM();
    CICKIDD CICKIDD = new CICKIDD();
    BPCPRMR BPCPRMR = new BPCPRMR();
    DCCIQVCH DCCIQVCH = new DCCIQVCH();
    CICOQVCH CICOQVCH = new CICOQVCH();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CICQVCH CICQVCH;
    public void MP(SCCGWA SCCGWA, CICQVCH CICQVCH) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.CICQVCH = CICQVCH;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CIZQVCH return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICQVCH.DATA.CI_NO);
        CEP.TRC(SCCGWA, CICQVCH.DATA.ENTY_TYP);
        CEP.TRC(SCCGWA, CICQVCH.DATA.AGR_NO);
        CEP.TRC(SCCGWA, CICQVCH.DATA.ID_TYPE);
        CEP.TRC(SCCGWA, CICQVCH.DATA.ID_NO);
        CEP.TRC(SCCGWA, CICQVCH);
        if (CICQVCH.DATA.AGR_NO.trim().length() > 0) {
            B030_BRW_VCH_PROC_BY_NO();
            if (pgmRtn) return;
        } else {
            B010_CHECK_INPUT_DATA();
            if (pgmRtn) return;
            B020_ACR_BRW_VCH_PROC();
            if (pgmRtn) return;
        }
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICQVCH.DATA.CI_NO);
        CEP.TRC(SCCGWA, CICQVCH.DATA.ENTY_TYP);
        CEP.TRC(SCCGWA, CICQVCH.DATA.AGR_NO);
        CEP.TRC(SCCGWA, CICQVCH.DATA.ID_TYPE);
        CEP.TRC(SCCGWA, CICQVCH.DATA.ID_NO);
        if (CICQVCH.DATA.CI_NO.trim().length() == 0) {
            if (CICQVCH.DATA.ID_TYPE.trim().length() > 0 
                && CICQVCH.DATA.ID_NO.trim().length() > 0 
                && CICQVCH.DATA.CI_NM.trim().length() > 0) {
                IBS.init(SCCGWA, CICKIDD);
                CICKIDD.DATA.ID_TYPE = CICQVCH.DATA.ID_TYPE;
                CICKIDD.DATA.ID_NO = CICQVCH.DATA.ID_NO;
                CICKIDD.DATA.CI_NM = CICQVCH.DATA.CI_NM;
                CICKIDD.FUNC = 'I';
                IBS.CALLCPN(SCCGWA, "CI-CHK-ID", CICKIDD);
                if (CICKIDD.RTN_FLG == '1') {
                    CICQVCH.DATA.CI_NO = CICKIDD.DATA.CI_NO;
                } else if (CICKIDD.RTN_FLG == '2') {
                    CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_INF_IS_NOT_UNIQ);
                } else if (CICKIDD.RTN_FLG == '5') {
                    CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_IS_NOT_EXIST);
                } else {
                }
            }
            if (CICQVCH.DATA.CI_NO.trim().length() == 0) {
                IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_IS_NOT_EXIST, CICQVCH.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
        CEP.TRC(SCCGWA, CICQVCH.DATA.CI_NO);
        if (CICQVCH.DATA.CI_NO.trim().length() > 0 
            && CICQVCH.DATA.JRL_FLG == 'Y') {
            IBS.init(SCCGWA, CIRJRL);
            CIRJRL.KEY.HCI_NO = CICQVCH.DATA.CI_NO;
            T000_READ_CITJRL();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, CIRJRL.KEY.JCI_NO);
        }
    }
    public void B020_ACR_BRW_VCH_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRACR);
        IBS.init(SCCGWA, CICOQVCH);
        B023_READ_FIRST_RECORD();
        if (pgmRtn) return;
        if (WS_ACR_FLG == 'Y') {
            B080_01_OUT_TITLE();
            if (pgmRtn) return;
            B024_GET_CINM();
            if (pgmRtn) return;
        } else {
            WS_FLG = 'N';
        }
        while (WS_ACR_FLG != 'N' 
            && SCCMPAG.FUNC != 'E') {
            if (CICQVCH.DATA.APP_FLG == 'Y') {
                if (CIRACR.FRM_APP.equalsIgnoreCase("LN") 
                    || CIRACR.FRM_APP.equalsIgnoreCase("IB")) {
                    B026_VCH_FOR_LNIB();
                    if (pgmRtn) return;
                } else {
                    B025_INFO_VCH();
                    if (pgmRtn) return;
                }
            } else {
                if (CIRACR.FRM_APP.equalsIgnoreCase("LN") 
                    || CIRACR.FRM_APP.equalsIgnoreCase("IB")) {
                } else {
                    B025_INFO_VCH();
                    if (pgmRtn) return;
                }
            }
            T000_READNEXT_CITACR();
            if (pgmRtn) return;
        }
        T000_ENDBR_CITACR();
        if (pgmRtn) return;
        if (WS_JRL_FLG == 'Y') {
            CICQVCH.DATA.CI_NO = CIRJRL.KEY.JCI_NO;
            B023_READ_FIRST_RECORD();
            if (pgmRtn) return;
            if (WS_ACR_FLG == 'Y' 
                && WS_FLG == 'N') {
                B080_01_OUT_TITLE();
                if (pgmRtn) return;
                B024_GET_CINM();
                if (pgmRtn) return;
            }
            while (WS_ACR_FLG != 'N' 
                && SCCMPAG.FUNC != 'E') {
                if (CICQVCH.DATA.APP_FLG == 'Y') {
                    if (CIRACR.FRM_APP.equalsIgnoreCase("LN") 
                        || CIRACR.FRM_APP.equalsIgnoreCase("IB")) {
                        B026_VCH_FOR_LNIB();
                        if (pgmRtn) return;
                    } else {
                        B025_INFO_VCH();
                        if (pgmRtn) return;
                    }
                } else {
                    if (CIRACR.FRM_APP.equalsIgnoreCase("LN") 
                        || CIRACR.FRM_APP.equalsIgnoreCase("IB")) {
                    } else {
                        B025_INFO_VCH();
                        if (pgmRtn) return;
                    }
                }
                T000_READNEXT_CITACR();
                if (pgmRtn) return;
            }
            T000_ENDBR_CITACR();
            if (pgmRtn) return;
        }
    }
    public void B030_BRW_VCH_PROC_BY_NO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRACR);
        CIRACR.KEY.AGR_NO = CICQVCH.DATA.AGR_NO;
        T000_READ_CITACR();
        if (pgmRtn) return;
        if (WS_ACR_FLG == 'N') {
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_ACR_INF_NOTEXIST, CICQVCH.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
            B080_01_OUT_TITLE();
            if (pgmRtn) return;
            B024_GET_CINM();
            if (pgmRtn) return;
            B025_INFO_VCH();
            if (pgmRtn) return;
        }
    }
    public void B025_INFO_VCH() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCIQVCH);
        DCCIQVCH.INPUT.IN_AC = CIRACR.KEY.AGR_NO;
        DCCIQVCH.INPUT.ENTRY_TYPE = CIRACR.ENTY_TYP;
        DCCIQVCH.INPUT.FROM_APP = CIRACR.FRM_APP;
        CIRBAS.KEY.CI_NO = CIRACR.CI_NO;
        T000_READ_CITBAS();
        if (pgmRtn) return;
        DCCIQVCH.INPUT.CI_TYPE = CIRBAS.CI_TYP;
        CEP.TRC(SCCGWA, DCCIQVCH.INPUT.IN_AC);
        CEP.TRC(SCCGWA, DCCIQVCH.INPUT.ENTRY_TYPE);
        CEP.TRC(SCCGWA, DCCIQVCH.INPUT.CI_TYPE);
        CEP.TRC(SCCGWA, DCCIQVCH.INPUT.FROM_APP);
        S000_CALL_DCZIQVCH();
        if (pgmRtn) return;
        for (WS_VCH_CNT = 1; WS_VCH_CNT <= 100 
            && DCCIQVCH.OUTPUT[WS_VCH_CNT-1].ACNO.trim().length() != 0; WS_VCH_CNT += 1) {
            CEP.TRC(SCCGWA, CICQVCH.DATA.CEL_FLG);
            if (CICQVCH.DATA.CEL_FLG == 'Y' 
                && (DCCIQVCH.OUTPUT[WS_VCH_CNT-1].VCH_STS == '6' 
                || DCCIQVCH.OUTPUT[WS_VCH_CNT-1].VCH_STS == '4')) {
            } else {
                IBS.init(SCCGWA, CICOQVCH);
                CICOQVCH.ENTY_TYP = CIRACR.ENTY_TYP;
                CICOQVCH.ENTY_NO = CIRACR.KEY.AGR_NO;
                CICOQVCH.OPEN_DT = CIRACR.OPEN_DT;
                CICOQVCH.OPEN_BR = CIRACR.OPN_BR;
                CICOQVCH.FRM_APP = CIRACR.FRM_APP;
                if (CIRBAS.CI_TYP == '1') {
                    CEP.TRC(SCCGWA, CIRBAS.KEY.CI_NO);
                    CICOQVCH.CI_CNM = WS_CI_CNM;
                    CICOQVCH.CI_ENM = WS_CI_ENM;
                } else {
