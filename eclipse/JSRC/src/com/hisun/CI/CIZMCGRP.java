package com.hisun.CI;

import com.hisun.SC.*;
import com.hisun.BP.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class CIZMCGRP {
    DBParm CITGRPM_RD;
    DBParm CITCGRP_RD;
    brParm CITCGRP_BR = new brParm();
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    SCCCALL SCCCALL = new SCCCALL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCFMT SCCFMT = new SCCFMT();
    String K_OUTPUT_FMT_X = "CIX01";
    String K_OUTPUT_FMT_9 = "CI102";
    String K_HIS_REMARKS_ADD = "ADD CLIENT GROUP";
    String K_HIS_REMARKS_CHG = "CHANGE CLIENT GROUP";
    String K_HIS_REMARKS_DEL = "DELETE CLIENT GROUP";
    int K_MAX_ROW = 10;
    int K_MAX_COL = 0;
    int K_COL_CNT = 0;
    CIZMCGRP_WS_CONSTANT WS_CONSTANT = new CIZMCGRP_WS_CONSTANT();
    CICOCGRP CICOCGRP = new CICOCGRP();
    CICOBCGR CICOBCGR = new CICOBCGR();
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    CIRGRPM CIRGRPM = new CIRGRPM();
    CIRCGRP CIRCGRP = new CIRCGRP();
    CIRCGRP CIRGRPO = new CIRCGRP();
    CIRCGRP CIRGRPN = new CIRCGRP();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CICMCGRP CICMCGRP;
    public void MP(SCCGWA SCCGWA, CICMCGRP CICMCGRP) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.CICMCGRP = CICMCGRP;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CIZMCGRP return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, CICMCGRP.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_DATA();
        if (pgmRtn) return;
        B020_CGRP_INF_PROC();
        if (pgmRtn) return;
    }
    public void B010_CHECK_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICMCGRP.FUNC);
        CEP.TRC(SCCGWA, CICMCGRP.CHECK_DATA);
    }
    public void B020_CGRP_INF_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICMCGRP.FUNC);
        if (CICMCGRP.FUNC == 'I') {
            B020_01_QUIRE_CGRP_INF();
            if (pgmRtn) return;
        } else if (CICMCGRP.FUNC == 'A') {
            B020_02_ADD_CGRP_INF();
            if (pgmRtn) return;
            B020_HISTORY_ADD();
            if (pgmRtn) return;
        } else if (CICMCGRP.FUNC == 'M') {
            B020_03_MODIFY_CGRP_INF();
            if (pgmRtn) return;
            B020_HISTORY_MODIFY();
            if (pgmRtn) return;
        } else if (CICMCGRP.FUNC == 'D') {
            B020_04_DELETE_CGRP_INF();
            if (pgmRtn) return;
            B020_HISTORY_DELETE();
            if (pgmRtn) return;
        } else if (CICMCGRP.FUNC == 'B') {
            B020_05_BROWSE_CGRP_INF();
            if (pgmRtn) return;
        } else {
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + CICMCGRP.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void B020_05_BROWSE_CGRP_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.MAX_COL_NO = 90;
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.SCR_COL_CNT = 0;
        SCCMPAG.SCR_ROW_CNT = 0;
        B_MPAG();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICMCGRP.CHECK_DATA);
        if (CICMCGRP.CHECK_DATA == 'N') {
            B020_05_Q_BRW_NO_OPT();
            if (pgmRtn) return;
        } else if (CICMCGRP.CHECK_DATA == 'M') {
            B020_05_Q_BRW_NAME_OPT();
            if (pgmRtn) return;
        } else if (CICMCGRP.CHECK_DATA == 'A') {
            B020_05_Q_BRW_ALL();
            if (pgmRtn) return;
        } else {
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + CICMCGRP.CHECK_DATA + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void B020_05_Q_BRW_NO_OPT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICMCGRP.INPUT_DATA.GRPS_NO);
        IBS.init(SCCGWA, CIRCGRP);
        CIRCGRP.KEY.GRPS_NO = CICMCGRP.INPUT_DATA.GRPS_NO;
        T000_READ_CGRP_RECORD_BY_NO();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "CGRP INF NOT FOUND");
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_GRP_NOTFND, CICMCGRP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        R000_01_OUT_TITLE();
        if (pgmRtn) return;
        R000_OUTPUT_DATA_FOR_BROWSE();
        if (pgmRtn) return;
    }
    public void B020_05_Q_BRW_NAME_OPT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRCGRP);
        CIRCGRP.NAME = CICMCGRP.INPUT_DATA.NAME;
        CIRCGRP.NAME = CIRCGRP.NAME.replace(" ", "%");
        T000_STARTBR_CITCGRP_BY_NM();
        if (pgmRtn) return;
        T000_READNEXT_CITCGRP();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "CGRP INF NOT FOUND");
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_GRP_NOTFND, CICMCGRP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        R000_01_OUT_TITLE();
        if (pgmRtn) return;
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1' 
            && SCCMPAG.FUNC != 'E') {
            CEP.TRC(SCCGWA, CIRCGRP.KEY.GRPS_NO);
            R000_OUTPUT_DATA_FOR_BROWSE();
            if (pgmRtn) return;
            T000_READNEXT_CITCGRP();
            if (pgmRtn) return;
        }
        T000_ENDBR_CITCGRP();
        if (pgmRtn) return;
    }
    public void B020_05_Q_BRW_ALL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRCGRP);
        T000_STARTBR_CITCGRP();
        if (pgmRtn) return;
        T000_READNEXT_CITCGRP();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "CGRP INF NOT FOUND");
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_GRP_NOTFND, CICMCGRP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        R000_01_OUT_TITLE();
        if (pgmRtn) return;
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1' 
            && SCCMPAG.FUNC != 'E') {
            CEP.TRC(SCCGWA, CIRCGRP.KEY.GRPS_NO);
            R000_OUTPUT_DATA_FOR_BROWSE();
            if (pgmRtn) return;
            T000_READNEXT_CITCGRP();
            if (pgmRtn) return;
        }
        T000_ENDBR_CITCGRP();
        if (pgmRtn) return;
    }
    public void B020_01_QUIRE_CGRP_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRCGRP);
        CIRCGRP.KEY.GRPS_NO = CICMCGRP.INPUT_DATA.GRPS_NO;
        T000_READ_CGRP_RECORD_BY_NO();
        if (pgmRtn) return;
        if (CICMCGRP.RETURN_INFO == 'N') {
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_GRP_NOTFND, CICMCGRP.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
            R000_OUTPUT_DATA_FOR_CGRP();
            if (pgmRtn) return;
        }
        R000_DATA_OUTPUT_FMT();
        if (pgmRtn) return;
    }
    public void B020_02_ADD_CGRP_INF() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICMCGRP.INPUT_DATA.GRPS_TYPE);
        IBS.init(SCCGWA, CIRCGRP);
        IBS.init(SCCGWA, CIRCGRP);
        CIRCGRP.KEY.GRPS_NO = CICMCGRP.INPUT_DATA.GRPS_NO;
        CEP.TRC(SCCGWA, CIRCGRP.KEY.GRPS_NO);
        T000_READUPDATE_CGRP_RECORD();
        if (pgmRtn) return;
        if (CICMCGRP.RETURN_INFO == 'F') {
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_GRP_RECORD_EXIST, CICMCGRP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        R000_MOVE_CGRP_DATA();
        if (pgmRtn) return;
        CIRCGRP.CRT_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        CIRCGRP.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        CIRCGRP.CRT_DT = SCCGWA.COMM_AREA.AC_DATE;
        IBS.CLONE(SCCGWA, CIRCGRP, CIRGRPN);
        T000_INSERT_CITCGRP();
        if (pgmRtn) return;
        R000_OUTPUT_DATA_FOR_CGRP();
        if (pgmRtn) return;
        R000_DATA_OUTPUT_FMT();
        if (pgmRtn) return;
    }
    public void B020_03_MODIFY_CGRP_INF() throws IOException,SQLException,Exception {
        R000_READUPDATE_CGRP_RECORD();
        if (pgmRtn) return;
        R000_MOVE_CGRP_DATA();
        if (pgmRtn) return;
        IBS.CLONE(SCCGWA, CIRCGRP, CIRGRPN);
        T000_REWRITE_CITCGRP();
        if (pgmRtn) return;
        R000_OUTPUT_DATA_FOR_CGRP();
        if (pgmRtn) return;
        R000_DATA_OUTPUT_FMT();
        if (pgmRtn) return;
    }
    public void B020_04_DELETE_CGRP_INF() throws IOException,SQLException,Exception {
        R000_READUPDATE_CGRP_RECORD();
        if (pgmRtn) return;
        R000_CHECK_GRPM_EXIST();
        if (pgmRtn) return;
        T000_DELETE_CITCGRP();
        if (pgmRtn) return;
        R000_OUTPUT_DATA_FOR_CGRP();
        if (pgmRtn) return;
        R000_DATA_OUTPUT_FMT();
        if (pgmRtn) return;
    }
    public void R000_READUPDATE_CGRP_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRCGRP);
        CIRCGRP.KEY.GRPS_NO = CICMCGRP.INPUT_DATA.GRPS_NO;
        T000_READUPDATE_CGRP_RECORD();
        if (pgmRtn) return;
        if (CICMCGRP.RETURN_INFO == 'N') {
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_GRP_NOTFND, CICMCGRP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.CLONE(SCCGWA, CIRCGRP, CIRGRPO);
    }
    public void R000_CHECK_GRPM_EXIST() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRGRPM);
        CIRGRPM.KEY.GRPS_NO = CIRCGRP.KEY.GRPS_NO;
        T000_READ_GRPM_FIRST();
        if (pgmRtn) return;
        if (WS_CONSTANT.WS_GRPM_FLG == 'Y') {
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_GRPME_NOTDEL, CICMCGRP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void R000_DATA_OUTPUT_FMT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        if (CICMCGRP.FUNC == 'I') {
            SCCFMT.FMTID = K_OUTPUT_FMT_X;
        } else {
            SCCFMT.FMTID = K_OUTPUT_FMT_9;
        }
        SCCFMT.DATA_PTR = CICOCGRP;
        SCCFMT.DATA_LEN = 722;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void R000_MOVE_CGRP_DATA() throws IOException,SQLException,Exception {
        CIRCGRP.KEY.GRPS_NO = CICMCGRP.INPUT_DATA.GRPS_NO;
        CIRCGRP.NAME = CICMCGRP.INPUT_DATA.NAME;
        CIRCGRP.TYPE = CICMCGRP.INPUT_DATA.GRPS_TYPE;
        CIRCGRP.BR = CICMCGRP.INPUT_DATA.BR;
        CIRCGRP.EFF_DATE = CICMCGRP.INPUT_DATA.EFF_DATE;
        CIRCGRP.EXP_DATE = CICMCGRP.INPUT_DATA.EXP_DATE;
        CIRCGRP.DEATIL = CICMCGRP.INPUT_DATA.DEATIL;
        CIRCGRP.FLG = CICMCGRP.INPUT_DATA.FLG;
        CIRCGRP.MACTH_TYPE1 = CICMCGRP.INPUT_DATA.MACTH_TYPE1;
        CIRCGRP.MACTH_TYPE2 = CICMCGRP.INPUT_DATA.MACTH_TYPE2;
        CIRCGRP.MACTH_TYPE3 = CICMCGRP.INPUT_DATA.MACTH_TYPE3;
        CIRCGRP.MACTH_TYPE4 = CICMCGRP.INPUT_DATA.MACTH_TYPE4;
        CIRCGRP.MACTH_TYPE5 = CICMCGRP.INPUT_DATA.MACTH_TYPE5;
        CIRCGRP.MACTH_KEY1 = CICMCGRP.INPUT_DATA.MACTH_KEY1;
        CIRCGRP.MACTH_KEY2 = CICMCGRP.INPUT_DATA.MACTH_KEY2;
        CIRCGRP.MACTH_KEY3 = CICMCGRP.INPUT_DATA.MACTH_KEY3;
        CIRCGRP.MACTH_KEY4 = CICMCGRP.INPUT_DATA.MACTH_KEY4;
        CIRCGRP.MACTH_KEY5 = CICMCGRP.INPUT_DATA.MACTH_KEY5;
        CIRCGRP.MACTH_DATA1 = CICMCGRP.INPUT_DATA.MACTH_DATA1;
        CIRCGRP.MACTH_DATA2 = CICMCGRP.INPUT_DATA.MACTH_DATA2;
        CIRCGRP.MACTH_DATA3 = CICMCGRP.INPUT_DATA.MACTH_DATA3;
        CIRCGRP.MACTH_DATA4 = CICMCGRP.INPUT_DATA.MACTH_DATA4;
        CIRCGRP.MACTH_DATA5 = CICMCGRP.INPUT_DATA.MACTH_DATA5;
        CIRCGRP.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        CIRCGRP.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        CIRCGRP.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
        CEP.TRC(SCCGWA, CIRCGRP.KEY.GRPS_NO);
        CEP.TRC(SCCGWA, CIRCGRP.NAME);
        CEP.TRC(SCCGWA, CIRCGRP.TYPE);
        CEP.TRC(SCCGWA, CIRCGRP.BR);
        CEP.TRC(SCCGWA, CIRCGRP.EFF_DATE);
        CEP.TRC(SCCGWA, CIRCGRP.EXP_DATE);
        CEP.TRC(SCCGWA, CIRCGRP.DEATIL.trim().length());
        CEP.TRC(SCCGWA, CIRCGRP.DEATIL);
        CEP.TRC(SCCGWA, CIRCGRP.FLG);
        CEP.TRC(SCCGWA, CIRCGRP.MACTH_TYPE1);
        CEP.TRC(SCCGWA, CIRCGRP.MACTH_TYPE2);
        CEP.TRC(SCCGWA, CIRCGRP.MACTH_TYPE3);
        CEP.TRC(SCCGWA, CIRCGRP.MACTH_TYPE4);
        CEP.TRC(SCCGWA, CIRCGRP.MACTH_TYPE5);
        CEP.TRC(SCCGWA, CIRCGRP.MACTH_KEY1);
        CEP.TRC(SCCGWA, CIRCGRP.MACTH_KEY2);
        CEP.TRC(SCCGWA, CIRCGRP.MACTH_KEY3);
        CEP.TRC(SCCGWA, CIRCGRP.MACTH_KEY4);
        CEP.TRC(SCCGWA, CIRCGRP.MACTH_KEY5);
        CEP.TRC(SCCGWA, CIRCGRP.MACTH_DATA1);
        CEP.TRC(SCCGWA, CIRCGRP.MACTH_DATA2);
        CEP.TRC(SCCGWA, CIRCGRP.MACTH_DATA3);
        CEP.TRC(SCCGWA, CIRCGRP.MACTH_DATA4);
        CEP.TRC(SCCGWA, CIRCGRP.MACTH_DATA5);
        CEP.TRC(SCCGWA, CIRCGRP.UPD_BR);
        CEP.TRC(SCCGWA, CIRCGRP.UPD_TLR);
        CEP.TRC(SCCGWA, CIRCGRP.UPD_DT);
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
    public void R000_OUTPUT_DATA_FOR_BROWSE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICOBCGR);
        CICOBCGR.OUTPUT_DATA.GRPS_NO = CIRCGRP.KEY.GRPS_NO;
        CICOBCGR.OUTPUT_DATA.GRPS_TYP = CIRCGRP.TYPE;
        CICOBCGR.OUTPUT_DATA.NAME = CIRCGRP.NAME;
        CICOBCGR.OUTPUT_DATA.EFF_DATE = CIRCGRP.EFF_DATE;
        CICOBCGR.OUTPUT_DATA.EXP_DATE = CIRCGRP.EXP_DATE;
        CICOBCGR.OUTPUT_DATA.FLG = CIRCGRP.FLG;
        S00_WRITE_TS_QUEUE();
        if (pgmRtn) return;
    }
    public void R000_OUTPUT_DATA_FOR_CGRP() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICOCGRP);
        CICOCGRP.OUTPUT_DATA.GRPS_NO = CIRCGRP.KEY.GRPS_NO;
        CICOCGRP.OUTPUT_DATA.GRPS_TYP = CIRCGRP.TYPE;
        CICOCGRP.OUTPUT_DATA.NAME = CIRCGRP.NAME;
        CICOCGRP.OUTPUT_DATA.BR = CIRCGRP.BR;
        CICOCGRP.OUTPUT_DATA.EFF_DATE = CIRCGRP.EFF_DATE;
        CICOCGRP.OUTPUT_DATA.EXP_DATE = CIRCGRP.EXP_DATE;
        CICOCGRP.OUTPUT_DATA.DEATIL = CIRCGRP.DEATIL;
        CICOCGRP.OUTPUT_DATA.FLG = CIRCGRP.FLG;
        CICOCGRP.OUTPUT_DATA.MACTH_TYPE1 = CIRCGRP.MACTH_TYPE1;
        CICOCGRP.OUTPUT_DATA.MACTH_TYPE2 = CIRCGRP.MACTH_TYPE2;
        CICOCGRP.OUTPUT_DATA.MACTH_TYPE3 = CIRCGRP.MACTH_TYPE3;
        CICOCGRP.OUTPUT_DATA.MACTH_TYPE4 = CIRCGRP.MACTH_TYPE4;
        CICOCGRP.OUTPUT_DATA.MACTH_TYPE5 = CIRCGRP.MACTH_TYPE5;
        CICOCGRP.OUTPUT_DATA.MACTH_KEY1 = CIRCGRP.MACTH_KEY1;
        CICOCGRP.OUTPUT_DATA.MACTH_KEY2 = CIRCGRP.MACTH_KEY2;
        CICOCGRP.OUTPUT_DATA.MACTH_KEY3 = CIRCGRP.MACTH_KEY3;
        CICOCGRP.OUTPUT_DATA.MACTH_KEY4 = CIRCGRP.MACTH_KEY4;
        CICOCGRP.OUTPUT_DATA.MACTH_KEY5 = CIRCGRP.MACTH_KEY5;
        CICOCGRP.OUTPUT_DATA.MACTH_DATA1 = CIRCGRP.MACTH_DATA1;
        CICOCGRP.OUTPUT_DATA.MACTH_DATA2 = CIRCGRP.MACTH_DATA2;
        CICOCGRP.OUTPUT_DATA.MACTH_DATA3 = CIRCGRP.MACTH_DATA3;
        CICOCGRP.OUTPUT_DATA.MACTH_DATA4 = CIRCGRP.MACTH_DATA4;
        CICOCGRP.OUTPUT_DATA.MACTH_DATA5 = CIRCGRP.MACTH_DATA5;
        CEP.TRC(SCCGWA, CICOCGRP.OUTPUT_DATA.GRPS_NO);
        CEP.TRC(SCCGWA, CICOCGRP.OUTPUT_DATA.GRPS_TYP);
        CEP.TRC(SCCGWA, CICOCGRP.OUTPUT_DATA.NAME);
        CEP.TRC(SCCGWA, CICOCGRP.OUTPUT_DATA.BR);
        CEP.TRC(SCCGWA, CICOCGRP.OUTPUT_DATA.EFF_DATE);
        CEP.TRC(SCCGWA, CICOCGRP.OUTPUT_DATA.EXP_DATE);
        CEP.TRC(SCCGWA, CICOCGRP.OUTPUT_DATA.DEATIL);
        CEP.TRC(SCCGWA, CICOCGRP.OUTPUT_DATA.FLG);
        CEP.TRC(SCCGWA, CICOCGRP.OUTPUT_DATA.MACTH_TYPE1);
        CEP.TRC(SCCGWA, CICOCGRP.OUTPUT_DATA.MACTH_TYPE2);
        CEP.TRC(SCCGWA, CICOCGRP.OUTPUT_DATA.MACTH_TYPE3);
        CEP.TRC(SCCGWA, CICOCGRP.OUTPUT_DATA.MACTH_TYPE4);
        CEP.TRC(SCCGWA, CICOCGRP.OUTPUT_DATA.MACTH_TYPE5);
        CEP.TRC(SCCGWA, CICOCGRP.OUTPUT_DATA.MACTH_KEY1);
        CEP.TRC(SCCGWA, CICOCGRP.OUTPUT_DATA.MACTH_KEY2);
        CEP.TRC(SCCGWA, CICOCGRP.OUTPUT_DATA.MACTH_KEY3);
        CEP.TRC(SCCGWA, CICOCGRP.OUTPUT_DATA.MACTH_KEY4);
        CEP.TRC(SCCGWA, CICOCGRP.OUTPUT_DATA.MACTH_KEY5);
        CEP.TRC(SCCGWA, CICOCGRP.OUTPUT_DATA.MACTH_DATA1);
        CEP.TRC(SCCGWA, CICOCGRP.OUTPUT_DATA.MACTH_DATA2);
        CEP.TRC(SCCGWA, CICOCGRP.OUTPUT_DATA.MACTH_DATA3);
        CEP.TRC(SCCGWA, CICOCGRP.OUTPUT_DATA.MACTH_DATA4);
        CEP.TRC(SCCGWA, CICOCGRP.OUTPUT_DATA.MACTH_DATA5);
    }
    public void B020_HISTORY_ADD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'A';
        BPCPNHIS.INFO.DATA_FLG = 'Y';
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.JRN_NO);
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        CEP.TRC(SCCGWA, BPCPNHIS.INFO.JRNNO);
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARKS_ADD;
        BPCPNHIS.INFO.REF_NO = CIRCGRP.KEY.GRPS_NO;
        BPCPNHIS.INFO.MAKER_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCPNHIS.INFO.FMT_ID = "CIRCGRP";
        BPCPNHIS.INFO.FMT_ID_LEN = 390;
        BPCPNHIS.INFO.OLD_DAT_PT = CIRGRPO;
        BPCPNHIS.INFO.NEW_DAT_PT = CIRGRPN;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void B020_HISTORY_MODIFY() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'M';
        BPCPNHIS.INFO.DATA_FLG = 'Y';
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.JRN_NO);
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        CEP.TRC(SCCGWA, BPCPNHIS.INFO.JRNNO);
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARKS_CHG;
        BPCPNHIS.INFO.REF_NO = CIRCGRP.KEY.GRPS_NO;
        BPCPNHIS.INFO.MAKER_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCPNHIS.INFO.FMT_ID = "CICOCGRP";
        BPCPNHIS.INFO.FMT_ID_LEN = 722;
        BPCPNHIS.INFO.OLD_DAT_PT = CICOCGRP;
        BPCPNHIS.INFO.NEW_DAT_PT = CICOCGRP;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void B020_HISTORY_DELETE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        IBS.init(SCCGWA, CICOCGRP);
        BPCPNHIS.INFO.TX_TYP = 'D';
        BPCPNHIS.INFO.DATA_FLG = 'Y';
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.JRN_NO);
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        CEP.TRC(SCCGWA, BPCPNHIS.INFO.JRNNO);
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARKS_DEL;
        BPCPNHIS.INFO.REF_NO = CIRCGRP.KEY.GRPS_NO;
        BPCPNHIS.INFO.MAKER_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCPNHIS.INFO.FMT_ID = "CICOCGRP";
        CICOCGRP.OUTPUT_DATA.GRPS_NO = CIRCGRP.KEY.GRPS_NO;
        BPCPNHIS.INFO.FMT_ID_LEN = 722;
        BPCPNHIS.INFO.OLD_DAT_PT = CICOCGRP;
        BPCPNHIS.INFO.NEW_DAT_PT = CICOCGRP;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void T000_READ_GRPM_FIRST() throws IOException,SQLException,Exception {
        CITGRPM_RD = new DBParm();
        CITGRPM_RD.TableName = "CITGRPM";
        CITGRPM_RD.where = "GRPS_NO = :CIRGRPM.KEY.GRPS_NO";
        CITGRPM_RD.fst = true;
        IBS.READ(SCCGWA, CIRGRPM, this, CITGRPM_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_CONSTANT.WS_GRPM_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_CONSTANT.WS_GRPM_FLG = 'N';
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "CITGRPM";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_CGRP_RECORD_BY_NO() throws IOException,SQLException,Exception {
        CITCGRP_RD = new DBParm();
        CITCGRP_RD.TableName = "CITCGRP";
        IBS.READ(SCCGWA, CIRCGRP, CITCGRP_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CICMCGRP.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CICMCGRP.RETURN_INFO = 'N';
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "CITCGRP";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READUPDATE_CGRP_RECORD() throws IOException,SQLException,Exception {
        CITCGRP_RD = new DBParm();
        CITCGRP_RD.TableName = "CITCGRP";
        CITCGRP_RD.eqWhere = "GRPS_NO";
        CITCGRP_RD.upd = true;
        IBS.READ(SCCGWA, CIRCGRP, CITCGRP_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CICMCGRP.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CICMCGRP.RETURN_INFO = 'N';
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "CITCGRP";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_INSERT_CITCGRP() throws IOException,SQLException,Exception {
        CITCGRP_RD = new DBParm();
        CITCGRP_RD.TableName = "CITCGRP";
        CITCGRP_RD.errhdl = true;
        IBS.WRITE(SCCGWA, CIRCGRP, CITCGRP_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_GRP_RECORD_EXIST, CICMCGRP.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "CITCGRP";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_CITCGRP() throws IOException,SQLException,Exception {
        CITCGRP_RD = new DBParm();
        CITCGRP_RD.TableName = "CITCGRP";
        IBS.REWRITE(SCCGWA, CIRCGRP, CITCGRP_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "CITCGRP";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_DELETE_CITCGRP() throws IOException,SQLException,Exception {
        CITCGRP_RD = new DBParm();
        CITCGRP_RD.TableName = "CITCGRP";
        CITCGRP_RD.where = "GRPS_NO = :CIRCGRP.KEY.GRPS_NO";
        IBS.DELETE(SCCGWA, CIRCGRP, this, CITCGRP_RD);
    }
    public void T000_STARTBR_CITCGRP_BY_NM() throws IOException,SQLException,Exception {
        CITCGRP_BR.rp = new DBParm();
        CITCGRP_BR.rp.TableName = "CITCGRP";
        CITCGRP_BR.rp.where = "NAME LIKE :CIRCGRP.NAME";
        IBS.STARTBR(SCCGWA, CIRCGRP, this, CITCGRP_BR);
    }
    public void T000_STARTBR_CITCGRP() throws IOException,SQLException,Exception {
        CITCGRP_BR.rp = new DBParm();
        CITCGRP_BR.rp.TableName = "CITCGRP";
        IBS.STARTBR(SCCGWA, CIRCGRP, CITCGRP_BR);
    }
    public void T000_READNEXT_CITCGRP() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, CIRCGRP, this, CITCGRP_BR);
    }
    public void T000_ENDBR_CITCGRP() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, CITCGRP_BR);
    }
    public void S00_WRITE_TS_QUEUE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_LEN = 90;
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, CICOBCGR);
        B_MPAG();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-REC-NHIS", BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
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
        if (CICMCGRP.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "CICMCGRP=");
            CEP.TRC(SCCGWA, CICMCGRP);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
