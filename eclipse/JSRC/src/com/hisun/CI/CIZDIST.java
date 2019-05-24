package com.hisun.CI;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.BP.BPRORGM;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;

public class CIZDIST {
    boolean pgmRtn = false;
    int K_MAX_ROW = 10;
    int K_MAX_COL = 99;
    int K_COL_CNT = 3;
    String WS_TRT_VIL = " ";
    String WS_OPN_VIL = " ";
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    CIRBAS CIRBAS = new CIRBAS();
    CIRNAM CIRNAM = new CIRNAM();
    CIRID CIRID = new CIRID();
    CIRACR CIRACR = new CIRACR();
    CICODIST CICODIST = new CICODIST();
    BPRORGM BPRORGM = new BPRORGM();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CICDIST CICDIST;
    public void MP(SCCGWA SCCGWA, CICDIST CICDIST) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.CICDIST = CICDIST;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CIZDIST return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, CICDIST.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B020_DIST_CI_NO();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRORGM);
        BPRORGM.KEY.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPTORGM_RD = new DBParm();
        BPTORGM_RD.TableName = "BPTORGM";
        IBS.READ(SCCGWA, BPRORGM, BPTORGM_RD);
        WS_TRT_VIL = BPRORGM.VIL_TYP;
    }
    public void B020_DIST_CI_NO() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICDIST.DATA.CI_NO);
        CEP.TRC(SCCGWA, CICDIST.DATA.ID_TYPE);
        CEP.TRC(SCCGWA, CICDIST.DATA.ID_NO);
        CEP.TRC(SCCGWA, CICDIST.DATA.CI_NM);
        CEP.TRC(SCCGWA, CICDIST.DATA.AGR_NO);
        if (CICDIST.DATA.CI_NO.trim().length() > 0) {
            B020_01_DIST_BY_CI_NO();
            if (pgmRtn) return;
        } else if (CICDIST.DATA.AGR_NO.trim().length() > 0) {
            B020_02_DIST_BY_AGR_NO();
            if (pgmRtn) return;
        } else if (CICDIST.DATA.ID_TYPE.trim().length() > 0 
                && CICDIST.DATA.ID_NO.trim().length() > 0 
                && CICDIST.DATA.CI_NM.trim().length() > 0) {
            B020_03_DIST_BY_IDNM();
            if (pgmRtn) return;
        } else if (CICDIST.DATA.ID_TYPE.trim().length() > 0 
                && CICDIST.DATA.ID_NO.trim().length() > 0) {
            B020_04_DIST_BY_ID();
            if (pgmRtn) return;
        } else if (CICDIST.DATA.CI_NM.trim().length() > 0) {
            B020_05_DIST_BY_NM();
            if (pgmRtn) return;
        } else {
            CEP.TRC(SCCGWA, "LACK OF NORMAL INFORMATION");
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT);
        }
    }
    public void B020_01_DIST_BY_CI_NO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRBAS);
        CIRBAS.KEY.CI_NO = CICDIST.DATA.CI_NO;
        T000_READ_CITBAS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            Z_RET();
            if (pgmRtn) return;
        }
        R000_01_OUT_TITLE();
        if (pgmRtn) return;
        R000_02_OUT_BRW_DATA();
        if (pgmRtn) return;
    }
    public void B020_02_DIST_BY_AGR_NO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRACR);
        CIRACR.KEY.AGR_NO = CICDIST.DATA.AGR_NO;
        T000_READ_CITACR();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, CIRBAS);
        CIRBAS.KEY.CI_NO = CIRACR.CI_NO;
        T000_READ_CITBAS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            Z_RET();
            if (pgmRtn) return;
        }
        R000_01_OUT_TITLE();
        if (pgmRtn) return;
        R000_02_OUT_BRW_DATA();
        if (pgmRtn) return;
    }
    public void B020_03_DIST_BY_IDNM() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRBAS);
        CIRBAS.ID_TYPE = CICDIST.DATA.ID_TYPE;
        CIRBAS.ID_NO = CICDIST.DATA.ID_NO;
        CIRBAS.CI_NM = CICDIST.DATA.CI_NM;
        BAS_CI_NM_LEN = CIRBAS.CI_NM.length();
        CITBAS_BR.rp = new DBParm();
        CITBAS_BR.rp.TableName = "CITBAS";
        CITBAS_BR.rp.eqWhere = "ID_TYPE,ID_NO,CI_NM";
        IBS.STARTBR(SCCGWA, CIRBAS, CITBAS_BR);
        IBS.READNEXT(SCCGWA, CIRBAS, this, CITBAS_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            Z_RET();
            if (pgmRtn) return;
        }
        R000_01_OUT_TITLE();
        if (pgmRtn) return;
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1') {
            IBS.init(SCCGWA, BPRORGM);
            BPRORGM.KEY.BR = CIRBAS.OPN_BR;
            BPTORGM_RD = new DBParm();
            BPTORGM_RD.TableName = "BPTORGM";
            IBS.READ(SCCGWA, BPRORGM, BPTORGM_RD);
            WS_OPN_VIL = BPRORGM.VIL_TYP;
            if (WS_OPN_VIL.equalsIgnoreCase(WS_TRT_VIL)) {
                R000_02_OUT_BRW_DATA();
                if (pgmRtn) return;
            }
            IBS.READNEXT(SCCGWA, CIRBAS, this, CITBAS_BR);
        }
        IBS.ENDBR(SCCGWA, CITBAS_BR);
    }
    public void B020_04_DIST_BY_ID() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRBAS);
        CIRBAS.ID_TYPE = CICDIST.DATA.ID_TYPE;
        CIRBAS.ID_NO = CICDIST.DATA.ID_NO;
        T000_STARTBR_CITBAS_BY_ID();
        if (pgmRtn) return;
        T000_READNEXT_CITBAS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        R000_01_OUT_TITLE();
        if (pgmRtn) return;
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1') {
            IBS.init(SCCGWA, BPRORGM);
            BPRORGM.KEY.BR = CIRBAS.OPN_BR;
            BPTORGM_RD = new DBParm();
            BPTORGM_RD.TableName = "BPTORGM";
            IBS.READ(SCCGWA, BPRORGM, BPTORGM_RD);
            CEP.TRC(SCCGWA, BPRORGM);
            CEP.TRC(SCCGWA, BPRORGM.VIL_TYP);
            CEP.TRC(SCCGWA, WS_OPN_VIL);
            WS_OPN_VIL = BPRORGM.VIL_TYP;
            if (WS_OPN_VIL.equalsIgnoreCase(WS_TRT_VIL)) {
                R000_02_OUT_BRW_DATA();
                if (pgmRtn) return;
            }
            T000_READNEXT_CITBAS();
            if (pgmRtn) return;
        }
        T000_ENDBR_CITBAS();
        if (pgmRtn) return;
        IBS.init(SCCGWA, CIRID);
        CIRID.KEY.ID_TYPE = CICDIST.DATA.ID_TYPE;
        CIRID.ID_NO = CICDIST.DATA.ID_NO;
        T000_STARTBR_CITID_BY_ID();
        if (pgmRtn) return;
        T000_READNEXT_CITID();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            Z_RET();
            if (pgmRtn) return;
        }
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1') {
            IBS.init(SCCGWA, CIRBAS);
            CIRBAS.KEY.CI_NO = CIRID.KEY.CI_NO;
            T000_READ_CITBAS();
            if (pgmRtn) return;
            if (CIRBAS.STSW == null) CIRBAS.STSW = "";
            JIBS_tmp_int = CIRBAS.STSW.length();
            for (int i=0;i<80-JIBS_tmp_int;i++) CIRBAS.STSW += " ";
            if (CIRBAS.STSW == null) CIRBAS.STSW = "";
            JIBS_tmp_int = CIRBAS.STSW.length();
            for (int i=0;i<80-JIBS_tmp_int;i++) CIRBAS.STSW += " ";
            if (CIRBAS.STSW.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("0") 
                && CIRBAS.STSW.substring(23 - 1, 23 + 1 - 1).equalsIgnoreCase("0")) {
                IBS.init(SCCGWA, BPRORGM);
                BPRORGM.KEY.BR = CIRBAS.OPN_BR;
                BPTORGM_RD = new DBParm();
                BPTORGM_RD.TableName = "BPTORGM";
                IBS.READ(SCCGWA, BPRORGM, BPTORGM_RD);
                WS_OPN_VIL = BPRORGM.VIL_TYP;
                if (WS_OPN_VIL.equalsIgnoreCase(WS_TRT_VIL)) {
                    R000_02_OUT_BRW_DATA_ID();
                    if (pgmRtn) return;
                }
            }
            T000_READNEXT_CITID();
            if (pgmRtn) return;
        }
        T000_ENDBR_CITID();
        if (pgmRtn) return;
    }
    public void B020_05_DIST_BY_NM() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRBAS);
        CIRBAS.CI_NM = CICDIST.DATA.CI_NM;
        BAS_CI_NM_LEN = CIRBAS.CI_NM.length();
        CEP.TRC(SCCGWA, BAS_CI_NM_LEN);
        if (BAS_CI_NM_LEN < 252) {
            BAS_CI_NM_LEN = BAS_CI_NM_LEN + 1;
            if (CIRBAS.CI_NM == null) CIRBAS.CI_NM = "";
            JIBS_tmp_int = CIRBAS.CI_NM.length();
            for (int i=0;i<252-JIBS_tmp_int;i++) CIRBAS.CI_NM += " ";
            CIRBAS.CI_NM = CIRBAS.CI_NM.substring(0, BAS_CI_NM_LEN - 1) + "%" + CIRBAS.CI_NM.substring(BAS_CI_NM_LEN + 1 - 1);
        }
        CEP.TRC(SCCGWA, BAS_CI_NM_LEN);
        CEP.TRC(SCCGWA, CIRBAS.CI_NM);
        T000_STARTBR_CITBAS_BY_NM();
        if (pgmRtn) return;
        T000_READNEXT_CITBAS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        R000_01_OUT_TITLE();
        if (pgmRtn) return;
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1' 
            && SCCMPAG.FUNC != 'E') {
            IBS.init(SCCGWA, BPRORGM);
            BPRORGM.KEY.BR = CIRBAS.OPN_BR;
            BPTORGM_RD = new DBParm();
            BPTORGM_RD.TableName = "BPTORGM";
            IBS.READ(SCCGWA, BPRORGM, BPTORGM_RD);
            WS_OPN_VIL = BPRORGM.VIL_TYP;
            if (WS_OPN_VIL.equalsIgnoreCase(WS_TRT_VIL)) {
                R000_02_OUT_BRW_DATA();
                if (pgmRtn) return;
            }
            T000_READNEXT_CITBAS();
            if (pgmRtn) return;
        }
        T000_ENDBR_CITBAS();
        if (pgmRtn) return;
        IBS.init(SCCGWA, CIRBAS);
        CIRBAS.CI_NM = CICDIST.DATA.CI_NM;
        BAS_CI_NM_LEN = CIRBAS.CI_NM.length();
        T000_STARTBR_CITBAS_BY_NM2();
        if (pgmRtn) return;
        T000_READNEXT_CITBAS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1' 
            && SCCMPAG.FUNC != 'E') {
            IBS.init(SCCGWA, BPRORGM);
            BPRORGM.KEY.BR = CIRBAS.OPN_BR;
            BPTORGM_RD = new DBParm();
            BPTORGM_RD.TableName = "BPTORGM";
            IBS.READ(SCCGWA, BPRORGM, BPTORGM_RD);
            WS_OPN_VIL = BPRORGM.VIL_TYP;
            if (WS_OPN_VIL.equalsIgnoreCase(WS_TRT_VIL)) {
                R000_02_OUT_BRW_DATA();
                if (pgmRtn) return;
            }
            T000_READNEXT_CITBAS();
            if (pgmRtn) return;
        }
        T000_ENDBR_CITBAS();
        if (pgmRtn) return;
    }
    public void R000_01_OUT_TITLE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.MAX_COL_NO = (short) K_MAX_COL;
        SCCMPAG.SCR_ROW_CNT = (short) K_MAX_ROW;
        SCCMPAG.SCR_COL_CNT = (short) K_COL_CNT;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void R000_02_OUT_BRW_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICODIST);
        CICODIST.DATA.CI_NO = CIRBAS.KEY.CI_NO;
        CICODIST.DATA.CI_TYP = CIRBAS.CI_TYP;
        CICODIST.DATA.CI_ATTR = CIRBAS.CI_ATTR;
        CICODIST.DATA.ID_TYPE = CIRBAS.ID_TYPE;
        CICODIST.DATA.ID_NO = CIRBAS.ID_NO;
        if (CIRBAS.CI_NM == null) CIRBAS.CI_NM = "";
        JIBS_tmp_int = CIRBAS.CI_NM.length();
        for (int i=0;i<252-JIBS_tmp_int;i++) CIRBAS.CI_NM += " ";
        CICODIST.DATA.CI_NM = CIRBAS.CI_NM.substring(0, BAS_CI_NM_LEN);
        CICODIST.DATA.CI_STSW = CIRBAS.STSW;
        CEP.TRC(SCCGWA, CIRBAS.VER_STSW);
        if (CIRBAS.CI_TYP == '1') {
            if (CIRBAS.VER_STSW == null) CIRBAS.VER_STSW = "";
            JIBS_tmp_int = CIRBAS.VER_STSW.length();
            for (int i=0;i<30-JIBS_tmp_int;i++) CIRBAS.VER_STSW += " ";
            if (CIRBAS.VER_STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1")) {
                CICODIST.DATA.VER_FLG = 'T';
            } else {
                CICODIST.DATA.VER_FLG = 'F';
            }
        }
        CICODIST.DATA.IDE_STSW = CIRBAS.IDE_STSW;
        CICODIST.DATA.SVR_LVL = CIRBAS.SVR_LVL;
        CICODIST.DATA.AUM_LVL = CIRBAS.SVR_LVL1;
        CICODIST.DATA.OPEN_BR = CIRBAS.OPN_BR;
        IBS.init(SCCGWA, CIRID);
        CIRID.KEY.CI_NO = CIRBAS.KEY.CI_NO;
        CIRID.KEY.ID_TYPE = CIRBAS.ID_TYPE;
        T000_READ_CITID();
        if (pgmRtn) return;
        CICODIST.DATA.ID_REMARK = CIRID.REMARK;
        IBS.init(SCCGWA, CIRNAM);
        CIRNAM.KEY.CI_NO = CIRBAS.KEY.CI_NO;
        CIRNAM.KEY.NM_TYPE = "03";
        T000_READ_CITNAM();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CICODIST.DATA.CI_ENM = CIRNAM.CI_NM;
        }
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, CICODIST);
        SCCMPAG.DATA_LEN = 840;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void R000_02_OUT_BRW_DATA_ID() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICODIST);
        CICODIST.DATA.CI_NO = CIRBAS.KEY.CI_NO;
        CICODIST.DATA.CI_TYP = CIRBAS.CI_TYP;
        CICODIST.DATA.CI_ATTR = CIRBAS.CI_ATTR;
        CICODIST.DATA.ID_TYPE = CIRBAS.ID_TYPE;
        CICODIST.DATA.ID_NO = CIRBAS.ID_NO;
        if (CIRBAS.CI_NM == null) CIRBAS.CI_NM = "";
        JIBS_tmp_int = CIRBAS.CI_NM.length();
        for (int i=0;i<252-JIBS_tmp_int;i++) CIRBAS.CI_NM += " ";
        CICODIST.DATA.CI_NM = CIRBAS.CI_NM.substring(0, BAS_CI_NM_LEN);
        CICODIST.DATA.CI_STSW = CIRBAS.STSW;
        CEP.TRC(SCCGWA, CIRBAS.VER_STSW);
        if (CIRBAS.VER_STSW == null) CIRBAS.VER_STSW = "";
        JIBS_tmp_int = CIRBAS.VER_STSW.length();
        for (int i=0;i<30-JIBS_tmp_int;i++) CIRBAS.VER_STSW += " ";
        if (CIRBAS.VER_STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1")) {
            CICODIST.DATA.VER_FLG = 'T';
        } else {
            CICODIST.DATA.VER_FLG = 'F';
        }
        CICODIST.DATA.IDE_STSW = CIRBAS.IDE_STSW;
        CICODIST.DATA.SVR_LVL = CIRBAS.SVR_LVL;
        CICODIST.DATA.AUM_LVL = CIRBAS.SVR_LVL1;
        CICODIST.DATA.OPEN_BR = CIRBAS.OPN_BR;
        CICODIST.DATA.ID_REMARK = CIRID.REMARK;
        IBS.init(SCCGWA, CIRNAM);
        CIRNAM.KEY.CI_NO = CIRBAS.KEY.CI_NO;
        CIRNAM.KEY.NM_TYPE = "03";
        T000_READ_CITNAM();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CICODIST.DATA.CI_ENM = CIRNAM.CI_NM;
        }
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, CICODIST);
        SCCMPAG.DATA_LEN = 840;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void T000_READ_CITBAS() throws IOException,SQLException,Exception {
        CITBAS_RD = new DBParm();
        CITBAS_RD.TableName = "CITBAS";
        IBS.READ(SCCGWA, CIRBAS, CITBAS_RD);
    }
    public void T000_STARTBR_CITBAS_BY_ID() throws IOException,SQLException,Exception {
        CITBAS_BR.rp = new DBParm();
        CITBAS_BR.rp.TableName = "CITBAS";
        CITBAS_BR.rp.eqWhere = "ID_TYPE,ID_NO";
        CITBAS_BR.rp.where = "SUBSTR ( STSW , 3 , 1 ) = '0' "
            + "AND SUBSTR ( STSW , 23 , 1 ) = '0'";
        IBS.STARTBR(SCCGWA, CIRBAS, this, CITBAS_BR);
    }
    public void T000_READ_CITBAS_BY_IDNM() throws IOException,SQLException,Exception {
        CITBAS_RD = new DBParm();
        CITBAS_RD.TableName = "CITBAS";
        CITBAS_RD.eqWhere = "ID_TYPE,ID_NO,CI_NM";
        CITBAS_RD.where = "SUBSTR ( STSW , 3 , 1 ) = '0' "
            + "AND SUBSTR ( STSW , 23 , 1 ) = '0'";
        IBS.READ(SCCGWA, CIRBAS, this, CITBAS_RD);
    }
    public void T000_STARTBR_CITBAS_BY_NM() throws IOException,SQLException,Exception {
