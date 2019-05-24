package com.hisun.CI;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.BP.BPCPNHIS;
import com.hisun.BP.BPCPRMR;
import com.hisun.BP.BPRPRMT;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;
import com.hisun.SC.SCCTPCL;

public class CIZCHDC {
    boolean pgmRtn = false;
    short K_MAX_ACRC_COUNT = 2;
    int WS_I = 0;
    int WS_MAX_SEQ = 0;
    char WS_SMS_FLG = ' ';
    short WS_ACRC_I = 0;
    char WS_ACRC_FLG = ' ';
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    CIRAGT CIRAGT = new CIRAGT();
    CIRAGT CIRAGTO = new CIRAGT();
    CIRAGT CIRAGTN = new CIRAGT();
    CIRACR CIRACR = new CIRACR();
    CIRACR CIRACRO = new CIRACR();
    CIRACR CIRACRN = new CIRACR();
    CIRACAC CIRACAC = new CIRACAC();
    CIRACAC CIRACACO = new CIRACAC();
    CIRACAC CIRACACN = new CIRACAC();
    CIRACRL CIRACRL = new CIRACRL();
    CIRACRL CIRACRLO = new CIRACRL();
    CIRACRL CIRACRLN = new CIRACRL();
    CIRONAC CIRONAC = new CIRONAC();
    CIRONAC CIRONACO = new CIRONAC();
    CIRONAC CIRONACN = new CIRONAC();
    CIRSTYP CIRSTYP = new CIRSTYP();
    CIRGOPP CIRGOPP = new CIRGOPP();
    SCCTPCL SCCTPCL = new SCCTPCL();
    CICCKLS CICCKLS = new CICCKLS();
    CIRHSTOT CIRHSTOT = new CIRHSTOT();
    CICPAGT CICPAGT = new CICPAGT();
    BPCPRMR BPCPRMR = new BPCPRMR();
    BPRPRMT BPRPRMT = new BPRPRMT();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CICCHDC CICCHDC;
    CICACRC CICACRC;
    public void MP(SCCGWA SCCGWA, CICCHDC CICCHDC) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.CICCHDC = CICCHDC;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CIZCHDC return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        CICACRC = (CICACRC) SCCGWA.COMM_AREA.CITACR_PTR;
        IBS.init(SCCGWA, CICCHDC.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        if (CICCHDC.FUNC == 'C') {
            B020_CHANGE_CARD();
            if (pgmRtn) return;
        } else if (CICCHDC.FUNC == 'A') {
            B030_CHANGE_AGT();
            if (pgmRtn) return;
        } else {
            CEP.TRC(SCCGWA, "FUNC INPUT ERROR");
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_FUNC_ERROR, CICCHDC.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICCHDC.DATA_FOR_CHANGE.ENTY_NO_OLD);
        CEP.TRC(SCCGWA, CICCHDC.DATA_FOR_CHANGE.ENTY_NO_NEW);
        if (CICCHDC.DATA_FOR_CHANGE.ENTY_NO_OLD.trim().length() == 0 
            || CICCHDC.DATA_FOR_CHANGE.ENTY_NO_NEW.trim().length() == 0) {
            CEP.TRC(SCCGWA, "OLD CARD AND NEW CARD MUST INPUT");
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_ENTY_NO_MUST_INPUT, CICCHDC.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, CIRACR);
        CIRACR.KEY.AGR_NO = CICCHDC.DATA_FOR_CHANGE.ENTY_NO_OLD;
        T000_READ_CITACR_EXIST();
        if (pgmRtn) return;
        IBS.init(SCCGWA, CIRACR);
        CIRACR.KEY.AGR_NO = CICCHDC.DATA_FOR_CHANGE.ENTY_NO_NEW;
        T000_READ_CITACR_EXIST();
        if (pgmRtn) return;
        IBS.init(SCCGWA, CICCKLS);
        CICCKLS.DATA.AGR_NO = CICCHDC.DATA_FOR_CHANGE.ENTY_NO_OLD;
        CICCKLS.DATA.AP_TYPE = "003";
        S000_CALL_CIZCKLS();
        if (pgmRtn) return;
    }
    public void B020_CHANGE_CARD() throws IOException,SQLException,Exception {
        B021_SAVE_CHANGE_CARD_HIS();
        if (pgmRtn) return;
        B022_TRANS_ACAC_TO_NEW_CARD();
        if (pgmRtn) return;
        B023_TRANS_AGT_TO_NEW_CARD();
        if (pgmRtn) return;
        B024_TRANS_ACRL_TO_NEW_CARD();
        if (pgmRtn) return;
        B025_DELETE_OLD_CARD();
        if (pgmRtn) return;
        B026_UPDATE_NEW_CARD();
        if (pgmRtn) return;
        B027_TRANS_STYP_TO_NEW_CARD();
        if (pgmRtn) return;
        B028_TRANS_GOPP_TO_NEW_CARD();
        if (pgmRtn) return;
        B029_TRANS_HSTOT_TO_NEW_CARD();
        if (pgmRtn) return;
        R000_TRANS_DATA_TO_ESB();
        if (pgmRtn) return;
    }
    public void B021_SAVE_CHANGE_CARD_HIS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRONAC);
        IBS.init(SCCGWA, CIRONACO);
        IBS.init(SCCGWA, CIRONACN);
        CIRONAC.O_AGR_NO = CICCHDC.DATA_FOR_CHANGE.ENTY_NO_OLD;
        CIRONAC.O_ENTY_TYP = CICCHDC.DATA_FOR_CHANGE.ENTY_TYP_OLD;
        CIRONAC.KEY.N_AGR_NO = CICCHDC.DATA_FOR_CHANGE.ENTY_NO_NEW;
        CIRONAC.KEY.N_ENTY_TYP = CICCHDC.DATA_FOR_CHANGE.ENTY_TYP_NEW;
        CIRONAC.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        CIRONAC.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        CIRONAC.CRT_DT = SCCGWA.COMM_AREA.AC_DATE;
        CIRONAC.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
        CIRONAC.CRT_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        CIRONAC.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        T000_WRITE_CITONAC();
        if (pgmRtn) return;
        IBS.CLONE(SCCGWA, CIRONAC, CIRONACN);
    }
    public void B022_TRANS_ACAC_TO_NEW_CARD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRACAC);
        CIRACAC.AGR_NO = CICCHDC.DATA_FOR_CHANGE.ENTY_NO_OLD;
        T000_STARTBR_CITACAC_UPD();
        if (pgmRtn) return;
        T000_READNEXT_CITACAC();
        if (pgmRtn) return;
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1') {
            if (CIRACAC.AID.trim().length() == 0 
                || CICCHDC.DATA_FOR_CHANGE.IC_AID_FLG != 'N') {
                CEP.TRC(SCCGWA, CIRACAC.KEY.ACAC_NO);
                CEP.TRC(SCCGWA, CIRACAC.AGR_NO);
                IBS.init(SCCGWA, CIRACACO);
                IBS.init(SCCGWA, CIRACACN);
                IBS.CLONE(SCCGWA, CIRACAC, CIRACACO);
                CIRACAC.AGR_NO = CICCHDC.DATA_FOR_CHANGE.ENTY_NO_NEW;
                CIRACAC.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
                CIRACAC.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
                CIRACAC.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
                T000_REWRITE_CITACAC();
                if (pgmRtn) return;
                IBS.CLONE(SCCGWA, CIRACAC, CIRACACN);
                if (CIRACAC.AGR_SEQ > WS_MAX_SEQ) {
                    WS_MAX_SEQ = CIRACAC.AGR_SEQ;
                }
            }
            T000_READNEXT_CITACAC();
            if (pgmRtn) return;
        }
        T000_ENDBR_CITACAC();
        if (pgmRtn) return;
    }
    public void B023_TRANS_AGT_TO_NEW_CARD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRAGT);
        CIRAGT.KEY.ENTY_NO = CICCHDC.DATA_FOR_CHANGE.ENTY_NO_OLD;
        CIRAGT.KEY.ENTY_TYP = CICCHDC.DATA_FOR_CHANGE.ENTY_TYP_OLD;
        T000_STARTBR_CITAGT_UPD();
        if (pgmRtn) return;
        T000_READNEXT_CITAGT();
        if (pgmRtn) return;
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1') {
            CEP.TRC(SCCGWA, CIRAGT.AGT_TYP);
            IBS.init(SCCGWA, CICPAGT);
            IBS.init(SCCGWA, BPCPRMR);
            IBS.init(SCCGWA, BPRPRMT);
            BPRPRMT.KEY.TYP = "CIAGT";
            BPRPRMT.KEY.CD = CIRAGT.AGT_TYP;
            BPCPRMR.DAT_PTR = BPRPRMT;
            S000_CALL_BPZPRMR();
            if (pgmRtn) return;
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRPRMT.DAT_TXT);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], CICPAGT);
            CEP.TRC(SCCGWA, CICPAGT.CHANGE_CARD);
            if (CICPAGT.CHANGE_CARD == 'Y') {
                CEP.TRC(SCCGWA, CIRAGT.KEY.AGT_NO);
                CEP.TRC(SCCGWA, CIRAGT.KEY.ENTY_NO);
                CEP.TRC(SCCGWA, CIRAGT.KEY.ENTY_TYP);
                IBS.init(SCCGWA, CIRAGTO);
                IBS.init(SCCGWA, CIRAGTN);
                IBS.CLONE(SCCGWA, CIRAGT, CIRAGTO);
                CIRAGT.KEY.ENTY_NO = CICCHDC.DATA_FOR_CHANGE.ENTY_NO_NEW;
                CIRAGT.KEY.ENTY_TYP = CICCHDC.DATA_FOR_CHANGE.ENTY_TYP_NEW;
                CIRAGT.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
                CIRAGT.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
                CIRAGT.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
                T000_REWRITE_CITAGT();
                if (pgmRtn) return;
                IBS.CLONE(SCCGWA, CIRAGT, CIRAGTN);
            }
            T000_READNEXT_CITAGT();
            if (pgmRtn) return;
        }
        T000_ENDBR_CITAGT();
        if (pgmRtn) return;
    }
    public void B024_TRANS_ACRL_TO_NEW_CARD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRACRL);
        CIRACRL.KEY.AC_NO = CICCHDC.DATA_FOR_CHANGE.ENTY_NO_OLD;
        T000_STARTBR_CITACRL_UPD1();
        if (pgmRtn) return;
        T000_READNEXT_CITACRL();
        if (pgmRtn) return;
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1') {
            CEP.TRC(SCCGWA, CIRACRL.KEY.AC_NO);
            CEP.TRC(SCCGWA, CIRACRL.KEY.AC_REL);
            CEP.TRC(SCCGWA, CIRACRL.KEY.REL_AC_NO);
            IBS.init(SCCGWA, CIRACRLO);
            IBS.init(SCCGWA, CIRACRLN);
            IBS.CLONE(SCCGWA, CIRACRL, CIRACRLO);
            CIRACRL.KEY.AC_NO = CICCHDC.DATA_FOR_CHANGE.ENTY_NO_NEW;
            CIRACRL.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
            CIRACRL.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
            CIRACRL.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            T000_REWRITE_CITACRL();
            if (pgmRtn) return;
            IBS.CLONE(SCCGWA, CIRACRL, CIRACRLN);
            T000_READNEXT_CITACRL();
            if (pgmRtn) return;
        }
        T000_ENDBR_CITACRL();
        if (pgmRtn) return;
        IBS.init(SCCGWA, CIRACRL);
        CIRACRL.KEY.REL_AC_NO = CICCHDC.DATA_FOR_CHANGE.ENTY_NO_OLD;
        T000_STARTBR_CITACRL_UPD2();
        if (pgmRtn) return;
        T000_READNEXT_CITACRL();
        if (pgmRtn) return;
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1') {
            CEP.TRC(SCCGWA, CIRACRL.KEY.AC_NO);
            CEP.TRC(SCCGWA, CIRACRL.KEY.AC_REL);
            CEP.TRC(SCCGWA, CIRACRL.KEY.REL_AC_NO);
            IBS.init(SCCGWA, CIRACRLO);
            IBS.init(SCCGWA, CIRACRLN);
            IBS.CLONE(SCCGWA, CIRACRL, CIRACRLO);
            CIRACRL.KEY.REL_AC_NO = CICCHDC.DATA_FOR_CHANGE.ENTY_NO_NEW;
            CIRACRL.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
            CIRACRL.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
            CIRACRL.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            T000_REWRITE_CITACRL();
            if (pgmRtn) return;
            IBS.CLONE(SCCGWA, CIRACRL, CIRACRLN);
            IBS.init(SCCGWA, BPCPNHIS);
            BPCPNHIS.INFO.TX_TYP = 'M';
            R000_WRT_HIS_ACRL();
            if (pgmRtn) return;
            T000_READNEXT_CITACRL();
            if (pgmRtn) return;
        }
        T000_ENDBR_CITACRL();
        if (pgmRtn) return;
    }
    public void B025_DELETE_OLD_CARD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRACR);
        IBS.init(SCCGWA, CIRACRO);
        IBS.init(SCCGWA, CIRACRN);
        CIRACR.KEY.AGR_NO = CICCHDC.DATA_FOR_CHANGE.ENTY_NO_OLD;
        T000_READ_CITACR_UPD();
        if (pgmRtn) return;
        IBS.CLONE(SCCGWA, CIRACR, CIRACRO);
        CIRACR.STS = '1';
        if (CIRACR.STSW == null) CIRACR.STSW = "";
        JIBS_tmp_int = CIRACR.STSW.length();
        for (int i=0;i<8-JIBS_tmp_int;i++) CIRACR.STSW += " ";
        CIRACR.STSW = CIRACR.STSW.substring(0, 8 - 1) + "1" + CIRACR.STSW.substring(8 + 1 - 1);
        CIRACR.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        CIRACR.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
        CIRACR.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        T000_REWRITE_CITACR();
        if (pgmRtn) return;
        IBS.CLONE(SCCGWA, CIRACR, CIRACRN);
        WS_SMS_FLG = CIRACR.SMS_FLG;
    }
    public void B026_UPDATE_NEW_CARD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRACR);
        CIRACR.KEY.AGR_NO = CICCHDC.DATA_FOR_CHANGE.ENTY_NO_NEW;
        T000_READ_CITACR_UPD();
        if (pgmRtn) return;
        CIRACR.AC_SEQ = WS_MAX_SEQ;
        CIRACR.SMS_FLG = WS_SMS_FLG;
        CIRACR.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        CIRACR.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
        CIRACR.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        T000_REWRITE_CITACR();
        if (pgmRtn) return;
    }
    public void B027_TRANS_STYP_TO_NEW_CARD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRSTYP);
        CIRSTYP.KEY.LC_OBJ = CICCHDC.DATA_FOR_CHANGE.ENTY_NO_OLD;
        T000_STARTBR_CITSTYP_BY_LCOBJ();
        if (pgmRtn) return;
        T000_READNEXT_CITSTYP();
        if (pgmRtn) return;
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1') {
            CIRSTYP.KEY.LC_OBJ = CICCHDC.DATA_FOR_CHANGE.ENTY_NO_NEW;
            CIRSTYP.CUS_AC = CICCHDC.DATA_FOR_CHANGE.ENTY_NO_NEW;
            CIRSTYP.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            CIRSTYP.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
            T000_REWRITE_CITSTYP();
            if (pgmRtn) return;
            T000_READNEXT_CITSTYP();
            if (pgmRtn) return;
        }
        T000_ENDBR_CITSTYP();
        if (pgmRtn) return;
    }
    public void B028_TRANS_GOPP_TO_NEW_CARD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRGOPP);
        CIRGOPP.KEY.LC_OBJ = CICCHDC.DATA_FOR_CHANGE.ENTY_NO_OLD;
        T000_STARTBR_CITGOPP_BY_LCOBJ();
        if (pgmRtn) return;
        T000_READNEXT_CITGOPP();
        if (pgmRtn) return;
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1') {
            CIRGOPP.KEY.LC_OBJ = CICCHDC.DATA_FOR_CHANGE.ENTY_NO_NEW;
            CIRGOPP.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            CIRGOPP.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
            T000_REWRITE_CITGOPP();
            if (pgmRtn) return;
            T000_READNEXT_CITGOPP();
            if (pgmRtn) return;
        }
        T000_ENDBR_CITGOPP();
        if (pgmRtn) return;
    }
    public void B029_TRANS_HSTOT_TO_NEW_CARD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRHSTOT);
        CIRHSTOT.KEY.LC_OBJ = CICCHDC.DATA_FOR_CHANGE.ENTY_NO_OLD;
        T000_STARTBR_CITHSTOT_BY_LCOBJ();
        if (pgmRtn) return;
        T000_READNEXT_CITHSTOT();
        if (pgmRtn) return;
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1') {
            CIRHSTOT.KEY.LC_OBJ = CICCHDC.DATA_FOR_CHANGE.ENTY_NO_NEW;
            CIRHSTOT.CUS_AC = CICCHDC.DATA_FOR_CHANGE.ENTY_NO_NEW;
            CIRHSTOT.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            CIRHSTOT.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
            T000_REWRITE_CITHSTOT();
            if (pgmRtn) return;
            T000_READNEXT_CITHSTOT();
            if (pgmRtn) return;
        }
        T000_ENDBR_CITHSTOT();
        if (pgmRtn) return;
    }
    public void B030_CHANGE_AGT() throws IOException,SQLException,Exception {
        for (WS_I = 1; WS_I <= 50 
            && CICCHDC.AGT_NO_AREA[WS_I-1].AGT_NO.trim().length() != 0; WS_I += 1) {
            CEP.TRC(SCCGWA, CICCHDC.AGT_NO_AREA[WS_I-1].AGT_NO);
            IBS.init(SCCGWA, CIRAGT);
            IBS.init(SCCGWA, CIRAGTO);
            IBS.init(SCCGWA, CIRAGTN);
            CIRAGT.KEY.AGT_NO = CICCHDC.AGT_NO_AREA[WS_I-1].AGT_NO;
            CIRAGT.KEY.ENTY_NO = CICCHDC.DATA_FOR_CHANGE.ENTY_NO_OLD;
            CIRAGT.KEY.ENTY_TYP = CICCHDC.DATA_FOR_CHANGE.ENTY_TYP_OLD;
            T000_READ_CITAGT_UPD();
            if (pgmRtn) return;
            if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                CEP.TRC(SCCGWA, "AGT INF NOT FOUND");
                IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_AGT_INF_NOTFND, CICCHDC.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            IBS.CLONE(SCCGWA, CIRAGT, CIRAGTO);
            CIRAGT.KEY.ENTY_NO = CICCHDC.DATA_FOR_CHANGE.ENTY_NO_NEW;
            CIRAGT.KEY.ENTY_TYP = CICCHDC.DATA_FOR_CHANGE.ENTY_TYP_NEW;
            CIRAGT.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
            CIRAGT.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
            CIRAGT.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            T000_REWRITE_CITAGT();
            if (pgmRtn) return;
            IBS.CLONE(SCCGWA, CIRAGT, CIRAGTN);
        }
    }
    public void R000_WRT_HIS_ACRL() throws IOException,SQLException,Exception {
        BPCPNHIS.INFO.DATA_FLG = 'Y';
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_CD = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        BPCPNHIS.INFO.MAKER_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCPNHIS.INFO.FMT_ID = "CIRACRL";
        BPCPNHIS.INFO.FMT_ID_LEN = 173;
        BPCPNHIS.INFO.AC = CIRACRL.KEY.AC_NO;
        BPCPNHIS.INFO.OLD_DAT_PT = CIRACRLO;
        BPCPNHIS.INFO.NEW_DAT_PT = CIRACRLN;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void R000_TRANS_DATA_TO_ESB() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCTPCL);
        SCCTPCL.SERV_AREA.OBJ_SYSTEM = "ESBP";
        SCCTPCL.SERV_AREA.SERV_CODE = "6001200000607";
        SCCTPCL.SERV_AREA.TRANS_FLG = '0';
        SCCTPCL.SERV_AREA.SERV_TYPE = ' ';
        SCCTPCL.INP_AREA.CC_CHANGE_CARD.CC_OLD_CARD_NO = CICCHDC.DATA_FOR_CHANGE.ENTY_NO_OLD;
        SCCTPCL.INP_AREA.SERV_DATA = IBS.CLS2CPY(SCCGWA, SCCTPCL.INP_AREA.CC_CHANGE_CARD);
        SCCTPCL.INP_AREA.CC_CHANGE_CARD.CC_NEW_CARD_NO = CICCHDC.DATA_FOR_CHANGE.ENTY_NO_NEW;
        SCCTPCL.INP_AREA.SERV_DATA = IBS.CLS2CPY(SCCGWA, SCCTPCL.INP_AREA.CC_CHANGE_CARD);
        SCCTPCL.INP_AREA.SERV_DATA_LEN = 64;
        IBS.CALLCPN(SCCGWA, "SC-LINK-EXTSERV", SCCTPCL);
    }
    public void R000_REWRITE_ACRC() throws IOException,SQLException,Exception {
        CICACRC.DATA[WS_ACRC_I-1].AGR_NO = CIRACR.KEY.AGR_NO;
        CICACRC.DATA[WS_ACRC_I-1].ENTY_TYP = CIRACR.ENTY_TYP;
        CICACRC.DATA[WS_ACRC_I-1].CI_NO = CIRACR.CI_NO;
        CICACRC.DATA[WS_ACRC_I-1].STS = CIRACR.STS;
        CICACRC.DATA[WS_ACRC_I-1].STSW = CIRACR.STSW;
        CICACRC.DATA[WS_ACRC_I-1].PROD_CD = CIRACR.PROD_CD;
        CICACRC.DATA[WS_ACRC_I-1].CNTRCT_TYP = CIRACR.CNTRCT_TYP;
        CICACRC.DATA[WS_ACRC_I-1].FRM_APP = CIRACR.FRM_APP;
        CICACRC.DATA[WS_ACRC_I-1].CCY = CIRACR.CCY;
        CICACRC.DATA[WS_ACRC_I-1].SHOW_FLG = CIRACR.SHOW_FLG;
        CICACRC.DATA[WS_ACRC_I-1].SMS_FLG = CIRACR.SMS_FLG;
        CICACRC.DATA[WS_ACRC_I-1].AC_CNM = CIRACR.AC_CNM;
        CICACRC.DATA[WS_ACRC_I-1].AC_ENM = CIRACR.AC_ENM;
        CICACRC.DATA[WS_ACRC_I-1].AC_SEQ = CIRACR.AC_SEQ;
        CICACRC.DATA[WS_ACRC_I-1].OPN_BR = CIRACR.OPN_BR;
        CICACRC.DATA[WS_ACRC_I-1].OPEN_DT = CIRACR.OPEN_DT;
        CICACRC.DATA[WS_ACRC_I-1].CLOSE_DT = CIRACR.CLOSE_DT;
        CICACRC.DATA[WS_ACRC_I-1].OWNER_BK = CIRACR.OWNER_BK;
        CICACRC.DATA[WS_ACRC_I-1].CRT_TLR = CIRACR.CRT_TLR;
        CICACRC.DATA[WS_ACRC_I-1].CRT_BR = CIRACR.CRT_BR;
        CICACRC.DATA[WS_ACRC_I-1].CRT_DT = CIRACR.CRT_DT;
        CICACRC.DATA[WS_ACRC_I-1].UPD_TLR = CIRACR.UPD_TLR;
        CICACRC.DATA[WS_ACRC_I-1].UPD_BR = CIRACR.UPD_BR;
        CICACRC.DATA[WS_ACRC_I-1].UPD_DT = CIRACR.UPD_DT;
        CICACRC.DATA[WS_ACRC_I-1].TS = CIRACR.TS;
        CEP.TRC(SCCGWA, WS_ACRC_I);
        CEP.TRC(SCCGWA, CICACRC.DATA[WS_ACRC_I-1].AGR_NO);
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-REC-NHIS", BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, BPCPNHIS.RC);
        }
    }
    public void S000_CALL_CIZCKLS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-CHECK-LST", CICCKLS);
        if (CICCKLS.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICCKLS.RC);
        }
    }
    public void S000_CALL_BPZPRMR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-PARM-READ", BPCPRMR);
        if (BPCPRMR.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMR.RC);
            if (JIBS_tmp_str[0].equalsIgnoreCase("BP0180")) {
                IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_AGT_TYP_NOT_DEF, CICCHDC.RC);
                Z_RET();
                if (pgmRtn) return;
            } else {
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMR.RC);
                IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], CICCHDC.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
    }
    public void T000_READ_CITACR_EXIST() throws IOException,SQLException,Exception {
        CITACR_RD = new DBParm();
        CITACR_RD.TableName = "CITACR";
        IBS.READ(SCCGWA, CIRACR, CITACR_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_ACR_INF_NOTEXIST, CICCHDC.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "CITACR";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_CITACR_UPD() throws IOException,SQLException,Exception {
        CITACR_RD = new DBParm();
        CITACR_RD.TableName = "CITACR";
        CITACR_RD.upd = true;
        IBS.READ(SCCGWA, CIRACR, CITACR_RD);
    }
    public void T000_REWRITE_CITACR() throws IOException,SQLException,Exception {
        CITACR_RD = new DBParm();
        CITACR_RD.TableName = "CITACR";
        IBS.REWRITE(SCCGWA, CIRACR, CITACR_RD);
    if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF ONL
        while (WS_ACRC_I < CICACRC.COUNT 
            && WS_ACRC_FLG != 'Y') {
            WS_ACRC_I = (short) (WS_ACRC_I + 1);
            CEP.TRC(SCCGWA, CIRACR.KEY.AGR_NO);
            if (CIRACR.KEY.AGR_NO.equalsIgnoreCase(CICACRC.DATA[WS_ACRC_I-1].AGR_NO)) {
                CEP.TRC(SCCGWA, "REWRITE ACR CASHE");
                R000_REWRITE_ACRC();
                if (pgmRtn) return;
                WS_ACRC_FLG = 'Y';
            }
        }
    } //FROM #ENDIF
    }
    public void T000_WRITE_CITONAC() throws IOException,SQLException,Exception {
        CITONAC_RD = new DBParm();
        CITONAC_RD.TableName = "CITONAC";
        IBS.WRITE(SCCGWA, CIRONAC, CITONAC_RD);
    }
    public void T000_STARTBR_CITACAC_UPD() throws IOException,SQLException,Exception {
        CITACAC_BR.rp = new DBParm();
        CITACAC_BR.rp.TableName = "CITACAC";
        CITACAC_BR.rp.eqWhere = "AGR_NO";
        CITACAC_BR.rp.upd = true;
        IBS.STARTBR(SCCGWA, CIRACAC, CITACAC_BR);
    }
    public void T000_READNEXT_CITACAC() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, CIRACAC, this, CITACAC_BR);
    }
    public void T000_REWRITE_CITACAC() throws IOException,SQLException,Exception {
        CITACAC_RD = new DBParm();
        CITACAC_RD.TableName = "CITACAC";
        IBS.REWRITE(SCCGWA, CIRACAC, CITACAC_RD);
    }
    public void T000_ENDBR_CITACAC() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, CITACAC_BR);
    }
    public void T000_STARTBR_CITAGT_UPD() throws IOException,SQLException,Exception {
