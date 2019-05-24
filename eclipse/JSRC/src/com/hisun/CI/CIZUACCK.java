package com.hisun.CI;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.BP.BPCPNHIS;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;

public class CIZUACCK {
    boolean pgmRtn = false;
    char WS_CHK_FLG = ' ';
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    CIRBAS CIRBAS = new CIRBAS();
    CIRACR CIRACR = new CIRACR();
    CIRACCK CIRACCK = new CIRACCK();
    CIRACCK CIRACCKO = new CIRACCK();
    CIRACCK CIRACCKN = new CIRACCK();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CICUACCK CICUACCK;
    public void MP(SCCGWA SCCGWA, CICUACCK CICUACCK) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.CICUACCK = CICUACCK;
        CEP.TRC(SCCGWA);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.JRN_NO);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CIZUACCK return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, CICUACCK.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B020_BROWSE_ACCK_INF();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (CICUACCK.DATA.CI_NM.trim().length() > 0 
                && CICUACCK.DATA.ID_TYPE.trim().length() > 0 
                && CICUACCK.DATA.ID_NO.trim().length() > 0) {
        } else if (CICUACCK.DATA.CI_NM.trim().length() == 0 
                && CICUACCK.DATA.ID_TYPE.trim().length() == 0 
                && CICUACCK.DATA.ID_NO.trim().length() == 0) {
        } else {
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_ID_NM_NOT_MATCH, CICUACCK.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B020_BROWSE_ACCK_INF() throws IOException,SQLException,Exception {
        if (CICUACCK.DATA.INQ_FLG == '1') {
            B021_BROWSE_BY_AC_NO();
            if (pgmRtn) return;
        } else if (CICUACCK.DATA.INQ_FLG == '2') {
            B022_BROWSE_BY_CI_NO();
            if (pgmRtn) return;
        } else if (CICUACCK.DATA.INQ_FLG == '0') {
            B023_BROWSE_BY_IDNM();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT, CICUACCK.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B021_BROWSE_BY_AC_NO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRACR);
        CIRACR.KEY.AGR_NO = CICUACCK.DATA.AC_NO;
        T000_READ_CITACR();
        if (pgmRtn) return;
        IBS.init(SCCGWA, CIRACCK);
        IBS.init(SCCGWA, CIRACCKO);
        IBS.init(SCCGWA, CIRACCKN);
        CIRACCK.KEY.AC_NO = CICUACCK.DATA.AC_NO;
        T000_READ_CITACCK_UPD();
        if (pgmRtn) return;
        IBS.CLONE(SCCGWA, CIRACCK, CIRACCKO);
        if ((CICUACCK.DATA.AC_TYPE == ' ' 
            || CICUACCK.DATA.AC_TYPE == CIRACCK.AC_TYPE) 
            && SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, "AAAA");
            CIRACCK.CHK_FLG = CICUACCK.DATA.CHK_FLG;
            CIRACCK.DOUBT_TP = CICUACCK.DATA.DOUBT_TP;
            CIRACCK.TREAT_TP = CICUACCK.DATA.TREAT_TP;
            CIRACCK.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
            CIRACCK.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
            CIRACCK.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            T000_REWRITE_CITACCK();
            if (pgmRtn) return;
            IBS.CLONE(SCCGWA, CIRACCK, CIRACCKN);
            IBS.init(SCCGWA, BPCPNHIS);
            BPCPNHIS.INFO.TX_TYP = 'M';
            R000_WRT_HIS_PROC();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_ACCK_INF_NOTFND, CICUACCK.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B022_BROWSE_BY_CI_NO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRBAS);
        CIRBAS.KEY.CI_NO = CICUACCK.DATA.CI_NO;
        T000_READ_CITBAS_EXIST();
        if (pgmRtn) return;
        IBS.init(SCCGWA, CIRACR);
        CIRACR.CI_NO = CICUACCK.DATA.CI_NO;
        T000_STARTBR_CITACR();
        if (pgmRtn) return;
        T000_READNEXT_CITACR();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "CI NO NOT FOUND");
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_BAS_INF_NOTFND, CICUACCK.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1' 
            && SCCMPAG.FUNC != 'E') {
            IBS.init(SCCGWA, CIRACCK);
            CIRACCK.KEY.AC_NO = CIRACR.KEY.AGR_NO;
            T000_READ_CITACCK_UPD();
            if (pgmRtn) return;
            if ((CICUACCK.DATA.AC_TYPE == ' ' 
                || CICUACCK.DATA.AC_TYPE == CIRACCK.AC_TYPE) 
                && SCCGWA.COMM_AREA.DBIO_FLG == '0') {
                CIRACCK.CHK_FLG = CICUACCK.DATA.CHK_FLG;
                CIRACCK.DOUBT_TP = CICUACCK.DATA.DOUBT_TP;
                CIRACCK.TREAT_TP = CICUACCK.DATA.TREAT_TP;
                CIRACCK.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
                CIRACCK.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
                CIRACCK.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
                T000_REWRITE_CITACCK();
                if (pgmRtn) return;
                IBS.CLONE(SCCGWA, CIRACCK, CIRACCKN);
                IBS.init(SCCGWA, BPCPNHIS);
                BPCPNHIS.INFO.TX_TYP = 'M';
                R000_WRT_HIS_PROC();
                if (pgmRtn) return;
                WS_CHK_FLG = '1';
            }
            T000_READNEXT_CITACR();
            if (pgmRtn) return;
        }
        T000_ENDBR_CITACR();
        if (pgmRtn) return;
        if (WS_CHK_FLG != '1') {
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.ALL_AC_NO_PROCESS, CICUACCK.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B023_BROWSE_BY_IDNM() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRBAS);
