package com.hisun.CI;

import com.hisun.SC.*;
import com.hisun.BP.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class CIZMGRPM {
    int JIBS_tmp_int;
    DBParm CITGRPM_RD;
    DBParm CITCGRP_RD;
    brParm CITGRPM_BR = new brParm();
    DBParm CITBAS_RD;
    DBParm CITACR_RD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    SCCCALL SCCCALL = new SCCCALL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCFMT SCCFMT = new SCCFMT();
    String K_OUTPUT_FMT_X = "CIX01";
    String K_OUTPUT_FMT_9 = "CI602";
    String K_HIS_REMARKS_ADD = "ADD CLIENT GROUP MEMBER";
    String K_HIS_REMARKS_DEL = "DELETE CLIENT GROUP MEMBER";
    char K_ENTY_TYP = '0';
    char K_DYNAMIC_FLG = '1';
    char K_UPD_RULS_FLG = '2';
    String K_OUTPUT_FMT = "CIB52";
    int K_MAX_ROW = 10;
    String WS_ERR_MSG = " ";
    String WS_REC = " ";
    short WS_REC_LEN = 0;
    short WS_GRPM_CNT = 0;
    int WS_GRPM_NO = 0;
    String WS_CI_NO = " ";
    int WS_I = 0;
    int WS_PAGE_ROW = 0;
    int WS_RECORD_NUM = 0;
    int WS_RECORD_NUM1 = 0;
    int WS_CX = 0;
    CIZMGRPM_WS_CONSTANT WS_CONSTANT = new CIZMGRPM_WS_CONSTANT();
    CICOGRPM CICOGRPM = new CICOGRPM();
    CICOBGRM CICOBGRM = new CICOBGRM();
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    CIRBAS CIRBAS = new CIRBAS();
    CIRACR CIRACR = new CIRACR();
    CIRGRPM CIRGRPM = new CIRGRPM();
    CIRGRPM CIRGRPMO = new CIRGRPM();
    CIRGRPM CIRGRPMN = new CIRGRPM();
    CIRCGRP CIRCGRP = new CIRCGRP();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    CICFB52 CICFB52 = new CICFB52();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA SCCGSCA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGCPT SCCGCPT;
    SCCGMSG SCCGMSG;
    CICMGRPM CICMGRPM;
    public void MP(SCCGWA SCCGWA, CICMGRPM CICMGRPM) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.CICMGRPM = CICMGRPM;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CIZMGRPM return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        IBS.init(SCCGWA, SCCEXCP);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_DATA();
        if (pgmRtn) return;
        B020_GRPM_INF_PROC();
        if (pgmRtn) return;
    }
    public void B010_CHECK_DATA() throws IOException,SQLException,Exception {
        if (CICMGRPM.INPUT_DATA.CI_NO.trim().length() == 0 
            && CICMGRPM.INPUT_DATA.AGR_NO.trim().length() > 0) {
            IBS.init(SCCGWA, CIRACR);
            CIRACR.KEY.AGR_NO = CICMGRPM.INPUT_DATA.AGR_NO;
            T000_READ_CITACR();
            if (pgmRtn) return;
            CICMGRPM.INPUT_DATA.CI_NO = CIRACR.CI_NO;
        }
        if (CICMGRPM.INPUT_DATA.CI_NO.trim().length() > 0) {
            IBS.init(SCCGWA, CIRBAS);
            CIRBAS.KEY.CI_NO = CICMGRPM.INPUT_DATA.CI_NO;
            T000_READ_CITBAS_BY_NO();
            if (pgmRtn) return;
            if (CICMGRPM.FUNC == 'A') {
                CEP.TRC(SCCGWA, CIRBAS.STSW);
                    if (CIRBAS.STSW == null) CIRBAS.STSW = "";
                    JIBS_tmp_int = CIRBAS.STSW.length();
                    for (int i=0;i<80-JIBS_tmp_int;i++) CIRBAS.STSW += " ";
                if (CIRBAS.STSW.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("1")) {
                    IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_STSW_IS_CLOSED, CICMGRPM.RC);
                    Z_RET();
                    if (pgmRtn) return;
                    if (CIRBAS.STSW == null) CIRBAS.STSW = "";
                    JIBS_tmp_int = CIRBAS.STSW.length();
                    for (int i=0;i<80-JIBS_tmp_int;i++) CIRBAS.STSW += " ";
                } else if (CIRBAS.STSW.substring(12 - 1, 12 + 1 - 1).equalsIgnoreCase("1")) {
                    IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_IS_DEAD, CICMGRPM.RC);
                    Z_RET();
                    if (pgmRtn) return;
                } else {
                }
            }
        }
        if (CICMGRPM.INPUT_DATA.GRPS_NO.trim().length() > 0) {
            IBS.init(SCCGWA, CIRCGRP);
            CIRCGRP.KEY.GRPS_NO = CICMGRPM.INPUT_DATA.GRPS_NO;
            T000_READ_CITCGRP();
            if (pgmRtn) return;
            if (CIRCGRP.EXP_DATE < SCCGWA.COMM_AREA.AC_DATE 
                && CIRCGRP.EXP_DATE != 0 
                && (CICMGRPM.FUNC == 'A' 
                || CICMGRPM.FUNC == 'M' 
                || CICMGRPM.FUNC == 'D')) {
                CEP.TRC(SCCGWA, "GROUP IS ENABLED");
                CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_CGRP_ENABLE);
            }
            if (CIRCGRP.FLG == '1') {
                IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_CANT_DYN_GRPS, CICMGRPM.RC);
            }
        }
    }
    public void B020_GRPM_INF_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICMGRPM.FUNC);
        if (CICMGRPM.FUNC == 'Q') {
            B020_01_QUIRE_GRPM_INF();
            if (pgmRtn) return;
        } else if (CICMGRPM.FUNC == 'A') {
            B020_02_ADD_GRPM_INF();
            if (pgmRtn) return;
    if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF ONL
            B020_HISTORY_ADD();
            if (pgmRtn) return;
    } //FROM #ENDIF
        } else if (CICMGRPM.FUNC == 'M') {
            B020_03_MOD_GRPM_INF();
            if (pgmRtn) return;
    if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF ONL
            B020_HISTORY_MOD();
            if (pgmRtn) return;
    } //FROM #ENDIF
        } else if (CICMGRPM.FUNC == 'D') {
            B020_04_DELETE_GRPM_INF();
            if (pgmRtn) return;
    if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF ONL
            B020_HISTORY_DEL();
            if (pgmRtn) return;
    } //FROM #ENDIF
        } else if (CICMGRPM.FUNC == 'B') {
            B020_05_BROWSE_GRPM_INF();
            if (pgmRtn) return;
        } else if (CICMGRPM.FUNC == '2') {
            B020_06_BROWSE2_GRPM_INF();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_FUNC_ERROR, CICMGRPM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B020_01_QUIRE_GRPM_INF() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICMGRPM.INPUT_DATA.GRPS_NO);
        CEP.TRC(SCCGWA, CICMGRPM.INPUT_DATA.CI_NO);
        IBS.init(SCCGWA, CIRGRPM);
        CIRGRPM.KEY.GRPS_NO = CICMGRPM.INPUT_DATA.GRPS_NO;
        CIRGRPM.KEY.ENTY_NO = CICMGRPM.INPUT_DATA.CI_NO;
        CIRGRPM.KEY.ENTY_TYP = K_ENTY_TYP;
        T000_READ_CITGRPM();
        if (pgmRtn) return;
        if (CICMGRPM.RETURN_INFO == 'N') {
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_GRPM_NOTFND, CICMGRPM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        R000_OUTPUT_DATA_FOR_GRPM();
        if (pgmRtn) return;
        R000_DATA_OUTPUT_FMT();
        if (pgmRtn) return;
    }
    public void B020_05_BROWSE_GRPM_INF() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICMGRPM.INPUT_DATA.GRPS_NO);
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.MAX_COL_NO = 431;
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.SCR_COL_CNT = 0;
        SCCMPAG.SCR_ROW_CNT = 0;
        B_MPAG();
        if (pgmRtn) return;
        if (CICMGRPM.INPUT_DATA.GRPS_NO.trim().length() == 0) {
            IBS.init(SCCGWA, CIRGRPM);
            CIRGRPM.KEY.ENTY_NO = CICMGRPM.INPUT_DATA.CI_NO;
            CIRGRPM.KEY.ENTY_TYP = K_ENTY_TYP;
            T000_STARTBR_BY_CI_NO();
            if (pgmRtn) return;
            T000_READ_NEXT_FOR_BROWSE();
            if (pgmRtn) return;
            if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                CEP.TRC(SCCGWA, "GRPM INF NOT EXIST");
                IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_GRPM_NOTFND, CICMGRPM.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            while (CICMGRPM.RETURN_INFO != 'E' 
                && SCCMPAG.FUNC != 'E') {
                IBS.init(SCCGWA, CIRCGRP);
                CIRCGRP.KEY.GRPS_NO = CIRGRPM.KEY.GRPS_NO;
                T000_READ_CITCGRP();
                if (pgmRtn) return;
                R000_OUTPUT_DATA_FOR_BROWSE();
                if (pgmRtn) return;
                T000_READ_NEXT_FOR_BROWSE();
                if (pgmRtn) return;
            }
            T000_ENDBR_FOR_BROWSE();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, CIRGRPM);
            CIRGRPM.KEY.GRPS_NO = CICMGRPM.INPUT_DATA.GRPS_NO;
            CIRGRPM.KEY.ENTY_NO = CICMGRPM.INPUT_DATA.CI_NO;
            CIRGRPM.KEY.ENTY_TYP = K_ENTY_TYP;
            T000_READ_CITGRPM();
            if (pgmRtn) return;
            if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                CEP.TRC(SCCGWA, "GRPM INF NOT EXIST");
                IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_GRPM_NOTFND, CICMGRPM.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            IBS.init(SCCGWA, CIRCGRP);
            CIRCGRP.KEY.GRPS_NO = CIRGRPM.KEY.GRPS_NO;
            T000_READ_CITCGRP();
            if (pgmRtn) return;
            R000_OUTPUT_DATA_FOR_BROWSE();
            if (pgmRtn) return;
        }
    }
    public void B020_06_BROWSE2_GRPM_INF() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICMGRPM.INPUT_DATA.CI_NO);
        IBS.init(SCCGWA, CIRBAS);
        IBS.init(SCCGWA, CIRGRPM);
        IBS.init(SCCGWA, CIRCGRP);
        IBS.init(SCCGWA, CICFB52);
        CIRBAS.KEY.CI_NO = CICMGRPM.INPUT_DATA.CI_NO;
        T000_READ_CITBAS();
        if (pgmRtn) return;
        CIRGRPM.KEY.ENTY_NO = CICMGRPM.INPUT_DATA.CI_NO;
        T000_STARTBR_CITGRPM();
        if (pgmRtn) return;
        T000_READNEXT_CITGRPM();
        if (pgmRtn) return;
        if (CICMGRPM.INPUT_DATA.PAGE_ROW > K_MAX_ROW 
            || CICMGRPM.INPUT_DATA.PAGE_ROW == 0) {
            WS_PAGE_ROW = K_MAX_ROW;
        } else {
            WS_PAGE_ROW = CICMGRPM.INPUT_DATA.PAGE_ROW;
        }
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1') {
            CEP.TRC(SCCGWA, WS_RECORD_NUM);
            WS_RECORD_NUM = WS_RECORD_NUM + 1;
            CEP.TRC(SCCGWA, WS_CX);
            CEP.TRC(SCCGWA, CICMGRPM.INPUT_DATA.PAGE_NUM);
            CEP.TRC(SCCGWA, WS_PAGE_ROW);
            WS_CX = ( CICMGRPM.INPUT_DATA.PAGE_NUM - 1 ) * WS_PAGE_ROW;
            CEP.TRC(SCCGWA, WS_RECORD_NUM);
            CEP.TRC(SCCGWA, WS_CX);
            CEP.TRC(SCCGWA, WS_I);
            if ((WS_RECORD_NUM > WS_CX) 
                && (WS_I < WS_PAGE_ROW)) {
                WS_I = WS_I + 1;
                CIRCGRP.KEY.GRPS_NO = CIRGRPM.KEY.GRPS_NO;
                T000_READ_CITCGRP();
                if (pgmRtn) return;
                B021_DATA_TRANS_TO_FMT();
                if (pgmRtn) return;
            }
            CEP.TRC(SCCGWA, WS_I);
            T000_READNEXT_CITGRPM();
            if (pgmRtn) return;
        }
        T000_ENDBR_CITGRPM();
        if (pgmRtn) return;
        B022_OUT_PAGE_TITLE();
        if (pgmRtn) return;
        B023_DATA_OUTPUT_FMT();
        if (pgmRtn) return;
    }
    public void R000_READUPDATE_GRPM_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRGRPM);
        IBS.init(SCCGWA, CIRGRPMO);
        IBS.init(SCCGWA, CIRGRPMN);
        CIRGRPM.KEY.GRPS_NO = CICMGRPM.INPUT_DATA.GRPS_NO;
        CIRGRPM.KEY.ENTY_NO = CICMGRPM.INPUT_DATA.CI_NO;
        if (CICMGRPM.INPUT_DATA.ENTY_TYP == ' ') {
            CIRGRPM.KEY.ENTY_TYP = K_ENTY_TYP;
        } else {
            CIRGRPM.KEY.ENTY_TYP = CICMGRPM.INPUT_DATA.ENTY_TYP;
        }
        T000_READUPDATE_GRPM_RECORD();
        if (pgmRtn) return;
        if (CICMGRPM.RETURN_INFO == 'N') {
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_GRPM_NOTFND, CICMGRPM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B020_02_ADD_GRPM_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRGRPM);
        IBS.init(SCCGWA, CIRGRPMO);
        IBS.init(SCCGWA, CIRGRPMN);
        if (CICMGRPM.INPUT_DATA.ENTY_TYP == ' ') {
            CIRGRPM.KEY.ENTY_TYP = K_ENTY_TYP;
        } else {
            CIRGRPM.KEY.ENTY_TYP = CICMGRPM.INPUT_DATA.ENTY_TYP;
        }
        CIRGRPM.KEY.GRPS_NO = CICMGRPM.INPUT_DATA.GRPS_NO;
        CIRGRPM.KEY.ENTY_NO = CICMGRPM.INPUT_DATA.CI_NO;
        CEP.TRC(SCCGWA, CIRGRPM.KEY.ENTY_TYP);
        CEP.TRC(SCCGWA, CIRGRPM.KEY.GRPS_NO);
        CEP.TRC(SCCGWA, CIRGRPM.KEY.ENTY_NO);
        T000_READ_CITGRPM();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, "GRPM INF EXIST");
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_GRPM_INF_EXIST, CICMGRPM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        R000_MOVE_GRPM_DATA();
        if (pgmRtn) return;
        T000_INSERT_CITGRPM();
        if (pgmRtn) return;
        IBS.CLONE(SCCGWA, CIRGRPM, CIRGRPMN);
        R000_OUTPUT_DATA_FOR_GRPM();
        if (pgmRtn) return;
        R000_DATA_OUTPUT_FMT();
        if (pgmRtn) return;
    }
    public void B020_03_MOD_GRPM_INF() throws IOException,SQLException,Exception {
        R000_READUPDATE_GRPM_RECORD();
        if (pgmRtn) return;
        IBS.CLONE(SCCGWA, CIRGRPM, CIRGRPMO);
        CIRGRPM.KEY.EFF_DT = CICMGRPM.INPUT_DATA.EFF_DATE;
        CIRGRPM.KEY.EXP_DT = CICMGRPM.INPUT_DATA.EXP_DATE;
        CIRGRPM.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        CIRGRPM.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        CIRGRPM.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
        T000_REWRITE_CITGRPM();
        if (pgmRtn) return;
        IBS.CLONE(SCCGWA, CIRGRPM, CIRGRPMN);
    }
    public void B020_04_DELETE_GRPM_INF() throws IOException,SQLException,Exception {
        R000_READUPDATE_GRPM_RECORD();
        if (pgmRtn) return;
        IBS.CLONE(SCCGWA, CIRGRPM, CIRGRPMO);
        T000_DELETE_CITGRPM();
        if (pgmRtn) return;
        R000_OUTPUT_DATA_FOR_GRPM();
        if (pgmRtn) return;
        R000_DATA_OUTPUT_FMT();
        if (pgmRtn) return;
    }
    public void R000_DATA_OUTPUT_FMT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT_X;
        if (CICMGRPM.FUNC == 'A' 
            || CICMGRPM.FUNC == 'D' 
            || CICMGRPM.FUNC == 'M') {
            SCCFMT.FMTID = K_OUTPUT_FMT_9;
        }
        SCCFMT.DATA_PTR = CICOGRPM;
        SCCFMT.DATA_LEN = 854;
        CEP.TRC(SCCGWA, SCCFMT.DATA_LEN);
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void R000_MOVE_GRPM_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRGRPM);
        CIRGRPM.KEY.GRPS_NO = CICMGRPM.INPUT_DATA.GRPS_NO;
        CIRGRPM.KEY.ENTY_NO = CICMGRPM.INPUT_DATA.CI_NO;
        if (CICMGRPM.INPUT_DATA.ENTY_TYP == ' ') {
            CIRGRPM.KEY.ENTY_TYP = K_ENTY_TYP;
        } else {
            CIRGRPM.KEY.ENTY_TYP = CICMGRPM.INPUT_DATA.ENTY_TYP;
        }
        CIRGRPM.KEY.EFF_DT = CICMGRPM.INPUT_DATA.EFF_DATE;
        CIRGRPM.KEY.EXP_DT = CICMGRPM.INPUT_DATA.EXP_DATE;
        CIRGRPM.CRT_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        CIRGRPM.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        CIRGRPM.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        CIRGRPM.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        CIRGRPM.CRT_DT = SCCGWA.COMM_AREA.AC_DATE;
        CIRGRPM.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
        CEP.TRC(SCCGWA, CIRGRPM.KEY.GRPS_NO);
        CEP.TRC(SCCGWA, CIRGRPM.KEY.ENTY_NO);
        CEP.TRC(SCCGWA, CIRGRPM.KEY.ENTY_TYP);
        CEP.TRC(SCCGWA, CIRGRPM.KEY.EFF_DT);
        CEP.TRC(SCCGWA, CIRGRPM.KEY.EXP_DT);
    }
    public void R000_OUTPUT_DATA_FOR_BROWSE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICOBGRM);
        CICOBGRM.OUTPUT_DATA.ENTY_NO = CIRGRPM.KEY.ENTY_NO;
        CICOBGRM.OUTPUT_DATA.ID_NO = CIRBAS.ID_NO;
        CICOBGRM.OUTPUT_DATA.ID_TYPE = CIRBAS.ID_TYPE;
        CICOBGRM.OUTPUT_DATA.CI_NM = CIRBAS.CI_NM;
        CICOBGRM.OUTPUT_DATA.GRPS_NO = CIRCGRP.KEY.GRPS_NO;
        CICOBGRM.OUTPUT_DATA.TYPE = CIRCGRP.TYPE;
        CICOBGRM.OUTPUT_DATA.NAME = CIRCGRP.NAME;
        CICOBGRM.OUTPUT_DATA.FLG = CIRCGRP.FLG;
        CICOBGRM.OUTPUT_DATA.EFF_DT = CIRGRPM.KEY.EFF_DT;
        CICOBGRM.OUTPUT_DATA.EXP_DT = CIRGRPM.KEY.EXP_DT;
        WS_REC = IBS.CLS2CPY(SCCGWA, CICOBGRM.OUTPUT_DATA);
        WS_REC_LEN = 431;
        S00_WRITE_TS_QUEUE();
        if (pgmRtn) return;
    }
    public void R000_OUTPUT_DATA_FOR_GRPM() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICOGRPM);
        CICOGRPM.OUTPUT_DATA.GRPS_NO = CIRGRPM.KEY.GRPS_NO;
        CICOGRPM.OUTPUT_DATA.CI_NO = CIRGRPM.KEY.ENTY_NO;
        CICOGRPM.OUTPUT_DATA.G_EFF_DATE = CIRGRPM.KEY.EFF_DT;
        CICOGRPM.OUTPUT_DATA.G_EXP_DATE = CIRGRPM.KEY.EXP_DT;
        CICOGRPM.OUTPUT_DATA.GRPS_TYP = CIRCGRP.TYPE;
        CICOGRPM.OUTPUT_DATA.NAME = CIRCGRP.NAME;
        CICOGRPM.OUTPUT_DATA.BR = CIRCGRP.BR;
        CICOGRPM.OUTPUT_DATA.C_EFF_DATE = CIRCGRP.EFF_DATE;
        CICOGRPM.OUTPUT_DATA.C_EXP_DATE = CIRCGRP.EXP_DATE;
        CICOGRPM.OUTPUT_DATA.DEATIL = CIRCGRP.DEATIL;
        CICOGRPM.OUTPUT_DATA.FLG = CIRCGRP.FLG;
        CICOGRPM.OUTPUT_DATA.ID_TYP = CIRBAS.ID_TYPE;
        CICOGRPM.OUTPUT_DATA.ID_NO = CIRBAS.ID_NO;
        CICOGRPM.OUTPUT_DATA.CI_NM = CIRBAS.CI_NM;
        CEP.TRC(SCCGWA, CICOGRPM.OUTPUT_DATA.GRPS_NO);
        CEP.TRC(SCCGWA, CICOGRPM.OUTPUT_DATA.CI_NO);
        CEP.TRC(SCCGWA, CICOGRPM.OUTPUT_DATA.GRPS_TYP);
        CEP.TRC(SCCGWA, CICOGRPM.OUTPUT_DATA.NAME);
        CEP.TRC(SCCGWA, CICOGRPM.OUTPUT_DATA.BR);
        CEP.TRC(SCCGWA, CICOGRPM.OUTPUT_DATA.C_EFF_DATE);
        CEP.TRC(SCCGWA, CICOGRPM.OUTPUT_DATA.C_EXP_DATE);
        CEP.TRC(SCCGWA, CICOGRPM.OUTPUT_DATA.DEATIL);
        CEP.TRC(SCCGWA, CICOGRPM.OUTPUT_DATA.FLG);
    }
    public void B020_HISTORY_ADD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'A';
        BPCPNHIS.INFO.DATA_FLG = 'Y';
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.JRN_NO);
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        S000_CALL_SCZGJRN();
        if (pgmRtn) return;
        GWA_BP_AREA.NFHIS_CUR_SEQ = 0;
    } //FROM #ENDIF
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        CEP.TRC(SCCGWA, BPCPNHIS.INFO.JRNNO);
        BPCPNHIS.INFO.TX_CD = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARKS_ADD;
        BPCPNHIS.INFO.REF_NO = CIRGRPM.KEY.GRPS_NO;
        BPCPNHIS.INFO.CI_NO = CIRGRPM.KEY.ENTY_NO;
        BPCPNHIS.INFO.MAKER_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCPNHIS.INFO.FMT_ID = "CIRGRPM";
        BPCPNHIS.INFO.FMT_ID_LEN = 128;
        BPCPNHIS.INFO.OLD_DAT_PT = CIRGRPMO;
        BPCPNHIS.INFO.NEW_DAT_PT = CIRGRPMN;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void B020_HISTORY_MOD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'M';
        BPCPNHIS.INFO.DATA_FLG = 'Y';
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_CD = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        BPCPNHIS.INFO.REF_NO = CIRGRPM.KEY.GRPS_NO;
        BPCPNHIS.INFO.CI_NO = CIRGRPM.KEY.ENTY_NO;
        BPCPNHIS.INFO.MAKER_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCPNHIS.INFO.FMT_ID = "CIRGRPM";
        BPCPNHIS.INFO.FMT_ID_LEN = 128;
        BPCPNHIS.INFO.OLD_DAT_PT = CIRGRPMO;
        BPCPNHIS.INFO.NEW_DAT_PT = CIRGRPMN;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void B020_HISTORY_DEL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'D';
        BPCPNHIS.INFO.DATA_FLG = 'Y';
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        S000_CALL_SCZGJRN();
        if (pgmRtn) return;
        GWA_BP_AREA.NFHIS_CUR_SEQ = 0;
    } //FROM #ENDIF
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.JRN_NO);
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        CEP.TRC(SCCGWA, BPCPNHIS.INFO.JRNNO);
        BPCPNHIS.INFO.TX_CD = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARKS_DEL;
        BPCPNHIS.INFO.REF_NO = CIRGRPM.KEY.GRPS_NO;
        BPCPNHIS.INFO.CI_NO = CIRGRPM.KEY.ENTY_NO;
        BPCPNHIS.INFO.MAKER_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCPNHIS.INFO.FMT_ID = "CIRGRPM";
        BPCPNHIS.INFO.FMT_ID_LEN = 128;
        BPCPNHIS.INFO.OLD_DAT_PT = CIRGRPMO;
        BPCPNHIS.INFO.NEW_DAT_PT = CIRGRPMN;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void B021_DATA_TRANS_TO_FMT() throws IOException,SQLException,Exception {
        CICFB52.DATA[WS_I-1].GRPS_NO = CIRGRPM.KEY.GRPS_NO;
        CICFB52.DATA[WS_I-1].TYPE = CIRCGRP.TYPE;
        CICFB52.DATA[WS_I-1].NAME = CIRCGRP.NAME;
        CICFB52.DATA[WS_I-1].FLG = CIRCGRP.FLG;
        CICFB52.DATA[WS_I-1].EFF_DATE = CIRGRPM.KEY.EFF_DT;
        CICFB52.DATA[WS_I-1].EXP_DATE = CIRGRPM.KEY.EXP_DT;
    }
    public void B022_OUT_PAGE_TITLE() throws IOException,SQLException,Exception {
        WS_RECORD_NUM1 = WS_RECORD_NUM % WS_PAGE_ROW;
        CICFB52.OUTPUT_TITLE.TOTAL_PAGE = (int) ((WS_RECORD_NUM - WS_RECORD_NUM1) / WS_PAGE_ROW);
        if (WS_RECORD_NUM1 > 0) {
            CICFB52.OUTPUT_TITLE.TOTAL_PAGE = CICFB52.OUTPUT_TITLE.TOTAL_PAGE + 1;
        }
        CICFB52.OUTPUT_TITLE.CI_NO = CIRBAS.KEY.CI_NO;
        CICFB52.OUTPUT_TITLE.ID_TYPE = CIRBAS.ID_TYPE;
        CICFB52.OUTPUT_TITLE.ID_NO = CIRBAS.ID_NO;
        CICFB52.OUTPUT_TITLE.CI_NM = CIRBAS.CI_NM;
        CICFB52.OUTPUT_TITLE.CI_TYP = CIRBAS.CI_TYP;
        CICFB52.OUTPUT_TITLE.CI_ATTR = CIRBAS.CI_ATTR;
        CICFB52.OUTPUT_TITLE.CI_SVR_LVL = CIRBAS.SVR_LVL;
        CICFB52.OUTPUT_TITLE.TOTAL_NUM = WS_RECORD_NUM;
        CICFB52.OUTPUT_TITLE.CURR_PAGE = CICMGRPM.INPUT_DATA.PAGE_NUM;
        CICFB52.OUTPUT_TITLE.PAGE_ROW = WS_I;
        if (CICFB52.OUTPUT_TITLE.CURR_PAGE >= CICFB52.OUTPUT_TITLE.TOTAL_PAGE 
            || CICFB52.OUTPUT_TITLE.TOTAL_PAGE == 0) {
            CICFB52.OUTPUT_TITLE.LAST_PAGE = 'Y';
        } else {
            CICFB52.OUTPUT_TITLE.LAST_PAGE = 'N';
        }
    }
    public void B023_DATA_OUTPUT_FMT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = CICFB52;
        SCCFMT.DATA_LEN = 1271;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void T000_READ_CITGRPM() throws IOException,SQLException,Exception {
        CITGRPM_RD = new DBParm();
        CITGRPM_RD.TableName = "CITGRPM";
        CITGRPM_RD.eqWhere = "GRPS_NO , ENTY_NO , ENTY_TYP";
        IBS.READ(SCCGWA, CIRGRPM, CITGRPM_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CICMGRPM.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CICMGRPM.RETURN_INFO = 'N';
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "CITGRPM";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_CITCGRP() throws IOException,SQLException,Exception {
        CITCGRP_RD = new DBParm();
        CITCGRP_RD.TableName = "CITCGRP";
        IBS.READ(SCCGWA, CIRCGRP, CITCGRP_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_CONSTANT.WS_GRPM_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_GRP_NOTFND, CICMGRPM.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "CITCGRP";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_NEXT_FOR_BROWSE() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, CIRGRPM, this, CITGRPM_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CICMGRPM.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CICMGRPM.RETURN_INFO = 'E';
            WS_CONSTANT.WS_GRPM_FLG = 'N';
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "CITGRPM";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_ENDBR_FOR_BROWSE() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, CITGRPM_BR);
    }
    public void T000_STARTBR_BY_CI_NO() throws IOException,SQLException,Exception {
        CITGRPM_BR.rp = new DBParm();
        CITGRPM_BR.rp.TableName = "CITGRPM";
        CITGRPM_BR.rp.eqWhere = "ENTY_TYP,ENTY_NO";
        IBS.STARTBR(SCCGWA, CIRGRPM, CITGRPM_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CICMGRPM.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CICMGRPM.RETURN_INFO = 'N';
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "CITGRPM";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READUPDATE_GRPM_RECORD() throws IOException,SQLException,Exception {
        CITGRPM_RD = new DBParm();
        CITGRPM_RD.TableName = "CITGRPM";
        CITGRPM_RD.eqWhere = "GRPS_NO , ENTY_NO , ENTY_TYP";
        CITGRPM_RD.upd = true;
        IBS.READ(SCCGWA, CIRGRPM, CITGRPM_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CICMGRPM.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CICMGRPM.RETURN_INFO = 'N';
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "CITGRPM";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_INSERT_CITGRPM() throws IOException,SQLException,Exception {
        CITGRPM_RD = new DBParm();
        CITGRPM_RD.TableName = "CITGRPM";
        CITGRPM_RD.errhdl = true;
        IBS.WRITE(SCCGWA, CIRGRPM, CITGRPM_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_GRP_RECORD_EXIST, CICMGRPM.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "CITGRPM";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_DELETE_CITGRPM() throws IOException,SQLException,Exception {
        CITGRPM_RD = new DBParm();
        CITGRPM_RD.TableName = "CITGRPM";
        CITGRPM_RD.where = "GRPS_NO = :CIRGRPM.KEY.GRPS_NO "
            + "AND ENTY_NO = :CIRGRPM.KEY.ENTY_NO";
        IBS.DELETE(SCCGWA, CIRGRPM, this, CITGRPM_RD);
    }
    public void T000_READ_CITBAS_BY_NO() throws IOException,SQLException,Exception {
        CITBAS_RD = new DBParm();
        CITBAS_RD.TableName = "CITBAS";
        CITBAS_RD.where = "CI_NO = :CIRBAS.KEY.CI_NO";
        CITBAS_RD.col = "CI_NO,STSW,CI_NM,ID_TYPE,ID_NO";
        IBS.READ(SCCGWA, CIRBAS, this, CITBAS_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_BAS_INF_NOTFND, CICMGRPM.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "CITBAS ";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_CITACR() throws IOException,SQLException,Exception {
        CITACR_RD = new DBParm();
        CITACR_RD.TableName = "CITACR";
        IBS.READ(SCCGWA, CIRACR, CITACR_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "ACR INF NOT FOUND");
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_ACR_INF_NOTFND, CICMGRPM.RC);
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
    public void T000_READ_CITBAS() throws IOException,SQLException,Exception {
        CITBAS_RD = new DBParm();
        CITBAS_RD.TableName = "CITBAS";
        IBS.READ(SCCGWA, CIRBAS, CITBAS_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_BAS_INF_NOTFND, CICMGRPM.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "CITBAS ";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_CITGRPM() throws IOException,SQLException,Exception {
        CITGRPM_BR.rp = new DBParm();
        CITGRPM_BR.rp.TableName = "CITGRPM";
        CITGRPM_BR.rp.eqWhere = "ENTY_NO";
        IBS.STARTBR(SCCGWA, CIRGRPM, CITGRPM_BR);
    }
    public void T000_READNEXT_CITGRPM() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, CIRGRPM, this, CITGRPM_BR);
    }
    public void T000_ENDBR_CITGRPM() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, CITGRPM_BR);
    }
    public void T000_REWRITE_CITGRPM() throws IOException,SQLException,Exception {
        CITGRPM_RD = new DBParm();
        CITGRPM_RD.TableName = "CITGRPM";
        IBS.REWRITE(SCCGWA, CIRGRPM, CITGRPM_RD);
    }
    public void S00_WRITE_TS_QUEUE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.DATA_PTR = WS_REC;
        SCCMPAG.DATA_LEN = WS_REC_LEN;
        SCCMPAG.FUNC = 'D';
        B_MPAG();
        if (pgmRtn) return;
    }
    public void S000_CALL_SCZGJRN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = "SC-GET-JOURNAL-NO";
        SCCCALL.COMMAREA_PTR = null;
        SCCCALL.CONTINUE_FLG = 'Y';
        IBS.CALL(SCCGWA, SCCCALL);
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-REC-NHIS", BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void B_MPAG() throws IOException,SQLException,Exception {
    if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF ONL
        JIBS_tmp_str[9] = "SCZMPAG";
        Class<?>clazz = Class.forName(JIBS_tmp_str[9].trim());
        Object obj = clazz.newInstance();
        Method m = clazz.getDeclaredMethod("MP",new Class[]{SCCGWA.getClass(), SCCMPAG.getClass()});
        m.invoke(obj, SCCGWA, SCCMPAG);
        if (SCCGWA.COMM_AREA.EXCP_FLG == 'Y') {
            Z_RET();
            if (pgmRtn) return;
        }
    } else { //FROM #ELSE
    } //FROM #ENDIF
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (CICMGRPM.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "CICMGRPM=");
            CEP.TRC(SCCGWA, CICMGRPM);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
