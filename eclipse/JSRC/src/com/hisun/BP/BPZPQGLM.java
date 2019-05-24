package com.hisun.BP;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.AI.*;
import com.hisun.SC.*;

public class BPZPQGLM {
    String JIBS_tmp_str[] = new String[10];
    DBParm AITGLM_RD;
    int WS_I = 0;
    int WS_J = 0;
    BPZPQGLM_WS_GLM_KEY WS_GLM_KEY = new BPZPQGLM_WS_GLM_KEY();
    short WS_GLM_LAST_BEGSEQ = 0;
    char WS_FND_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    AICMSG_ERROR_MSG AICMSG_ERROR_MSG = new AICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    BPCPRMR BPCPRMR = new BPCPRMR();
    AIRGLM AIRGLM = new AIRGLM();
    AICGLM AICGLMC = new AICGLM();
    BPCPRMBR BPCPRMBR = new BPCPRMBR();
    SCCGWA SCCGWA;
    BPCPQGLM BPCPQGLM;
    SCCCWA SCCCWA;
    String LK_MMT = " ";
    public void MP(SCCGWA SCCGWA, BPCPQGLM BPCPQGLM) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCPQGLM = BPCPQGLM;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPZPQGLM return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        WS_FND_FLG = ' ';
        IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_NORMAL, BPCPQGLM.RC);
    if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF ONL
        R000_SET_LK_MMT();
    } //FROM #ENDIF
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_QUERY_BPRGLM_PROC();
    }
    public void B010_QUERY_BPRGLM_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AIRGLM);
        AIRGLM.KEY.IBS_AC_BK = SCCGWA.COMM_AREA.TR_BANK;
        AIRGLM.KEY.GL_MSTNO = BPCPQGLM.DAT.INPUT.MSTNO;
        AIRGLM.KEY.EFF_DT = SCCGWA.COMM_AREA.AC_DATE;
        AIRGLM.KEY.EXP_DT = SCCGWA.COMM_AREA.AC_DATE;
        IBS.init(SCCGWA, WS_GLM_KEY);
        WS_GLM_KEY.WS_AI_AC_BK = SCCGWA.COMM_AREA.TR_BANK;
        WS_GLM_KEY.WS_AI_GL_MSTNO = BPCPQGLM.DAT.INPUT.MSTNO;
        CEP.TRC(SCCGWA, BPCPQGLM.DAT.INPUT.MSTNO);
        CEP.TRC(SCCGWA, WS_GLM_KEY);
        T000_READ_AITGLM();
        CEP.TRC(SCCGWA, AIRGLM.KEY.EFF_DT);
        CEP.TRC(SCCGWA, AIRGLM.KEY.EXP_DT);
        if (WS_FND_FLG == 'N') {
            IBS.CPY2CLS(SCCGWA, AICMSG_ERROR_MSG.AI_AITGLM_NOTFND, BPCPQGLM.RC);
        }
        if (AIRGLM.KEY.EFF_DT > SCCGWA.COMM_AREA.AC_DATE) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.AM_GLMST_EFF_DT_ERR, BPCPQGLM.RC);
        }
        if (AIRGLM.KEY.EXP_DT < SCCGWA.COMM_AREA.AC_DATE) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.AM_GLMST_EXP_DT_ERR, BPCPQGLM.RC);
        }
        WS_I = 0;
        IBS.init(SCCGWA, AICGLMC);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, AIRGLM);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], AICGLMC);
        CEP.TRC(SCCGWA, AIRGLM);
        CEP.TRC(SCCGWA, AICGLMC);
        BPCPQGLM.DAT.OUTPUT.COA_FLG = AICGLMC.DATA.COA_FLG;
        BPCPQGLM.DAT.OUTPUT.SNAME = AICGLMC.DATA.DESC;
        BPCPQGLM.DAT.OUTPUT.LNAME = AICGLMC.DATA.CDESC;
        BPCPQGLM.DAT.OUTPUT.EFF_DATE = AICGLMC.EFF_DT;
        BPCPQGLM.DAT.OUTPUT.EXP_DATE = AICGLMC.EXP_DT;
        BPCPQGLM.DAT.OUTPUT.OPT_FLG = AICGLMC.DATA.OPT_FLG;
        BPCPQGLM.DAT.OUTPUT.CNTY1 = AICGLMC.DATA.CNTY1;
        BPCPQGLM.DAT.OUTPUT.CNTY2 = AICGLMC.DATA.CNTY2;
        BPCPQGLM.DAT.OUTPUT.CNTY3 = AICGLMC.DATA.CNTY3;
        BPCPQGLM.DAT.OUTPUT.CKFLG = AICGLMC.DATA.CKFLG;
        BPCPQGLM.DAT.OUTPUT.REAL_GL = AICGLMC.DATA.REAL_GL;
        BPCPQGLM.DAT.OUTPUT.MEMO_GL = AICGLMC.DATA.MEMO_GL;
        for (WS_I = 1; WS_I <= 60; WS_I += 1) {
            BPCPQGLM.DAT.OUTPUT.REL_ITMS[WS_I-1].ITM_NO = AICGLMC.DATA.REL_ITMS[WS_I-1].ITM_NO;
            BPCPQGLM.DAT.OUTPUT.REL_ITMS[WS_I-1].ITM_SEQ = AICGLMC.DATA.REL_ITMS[WS_I-1].ITM_SEQ;
            BPCPQGLM.DAT.OUTPUT.REL_ITMS[WS_I-1].REMARK = AICGLMC.DATA.REL_ITMS[WS_I-1].REMARK;
        }
        BPCPQGLM.DAT.OUTPUT.UPD_TLR = AICGLMC.DATA.UPD_TLR;
        BPCPQGLM.DAT.OUTPUT.UPD_DATE = AICGLMC.DATA.UPD_DATE;
        BPCPQGLM.DAT.OUTPUT.UPD_TIME = AICGLMC.DATA.UPD_TIME;
        BPCPQGLM.DAT.OUTPUT.SUP_TEL1 = AICGLMC.DATA.SUP_TEL1;
        BPCPQGLM.DAT.OUTPUT.SUP_TEL2 = AICGLMC.DATA.SUP_TEL2;
        CEP.TRC(SCCGWA, BPCPQGLM.DAT.OUTPUT.COA_FLG);
        CEP.TRC(SCCGWA, BPCPQGLM.DAT.OUTPUT.SNAME);
        CEP.TRC(SCCGWA, BPCPQGLM.DAT.OUTPUT.LNAME);
        CEP.TRC(SCCGWA, BPCPQGLM.DAT.OUTPUT.EFF_DATE);
        CEP.TRC(SCCGWA, BPCPQGLM.DAT.OUTPUT.EXP_DATE);
        CEP.TRC(SCCGWA, BPCPQGLM.DAT.OUTPUT.OPT_FLG);
    }
    public void T000_READ_AITGLM() throws IOException,SQLException,Exception {
    if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF ONL
        if (SCCGWA.COMM_AREA.PARM_IND == 'E') {
            WS_J = 0;
            WS_J = 1;
            SCCGWA.COMM_AREA.DBIO_FLG = '0';
            WS_FND_FLG = 'N';
            IBS.init(SCCGWA, BPCPRMBR.CD);
            BPCPRMBR.TYP = "AITGLM";
            BPCPRMBR.CD.KEY = IBS.CLS2CPY(SCCGWA, WS_GLM_KEY);
            BPCPRMBR.LTH = 4767;
            BPCPRMBR.CD.KEY_KEYSEQ = (short) WS_J;
            BPCPRMBR.CD.KEY_BEGSEQ = WS_GLM_LAST_BEGSEQ;
            AIRGLM = (AIRGLM) IBS.HASHLKUP(SCCGWA, "AIRGLM", BPCPRMBR.CD);
            WS_J += 1;
            WS_GLM_LAST_BEGSEQ = 0;
            WS_GLM_LAST_BEGSEQ = BPCPRMBR.CD.KEY_BEGSEQ;
            CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
            if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
                AITGLM_RD = new DBParm();
                AITGLM_RD.TableName = "AITGLM";
                AITGLM_RD.where = "IBS_AC_BK = :AIRGLM.KEY.IBS_AC_BK "
                    + "AND GL_MSTNO = :AIRGLM.KEY.GL_MSTNO "
                    + "AND EFF_DT <= :AIRGLM.KEY.EFF_DT "
                    + "AND EXP_DT >= :AIRGLM.KEY.EXP_DT";
                IBS.READ(SCCGWA, AIRGLM, this, AITGLM_RD);
            }
        } else {
            AITGLM_RD = new DBParm();
            AITGLM_RD.TableName = "AITGLM";
            AITGLM_RD.where = "IBS_AC_BK = :AIRGLM.KEY.IBS_AC_BK "
                + "AND GL_MSTNO = :AIRGLM.KEY.GL_MSTNO "
                + "AND EFF_DT <= :AIRGLM.KEY.EFF_DT "
                + "AND EXP_DT >= :AIRGLM.KEY.EXP_DT";
            IBS.READ(SCCGWA, AIRGLM, this, AITGLM_RD);
        }
    } else { //FROM #ELSE
        AITGLM_RD = new DBParm();
        AITGLM_RD.TableName = "AITGLM";
        AITGLM_RD.where = "IBS_AC_BK = :AIRGLM.KEY.IBS_AC_BK "
            + "AND GL_MSTNO = :AIRGLM.KEY.GL_MSTNO "
            + "AND EFF_DT <= :AIRGLM.KEY.EFF_DT "
            + "AND EXP_DT >= :AIRGLM.KEY.EXP_DT";
        IBS.READ(SCCGWA, AIRGLM, this, AITGLM_RD);
    } //FROM #ENDIF
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_FND_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_FND_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "AITGLM";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
        }
    }
    public void R000_SET_LK_MMT() throws IOException,SQLException,Exception {
        SCCCWA = (SCCCWA) SCCGWA.COMM_AREA.CWA_PTR;
        if (BPCPRMBR.TYP.equalsIgnoreCase("CITDTL")
            || BPCPRMBR.TYP.equalsIgnoreCase("CITDINF")
            || BPCPRMBR.TYP.equalsIgnoreCase("AITENTY")
            || BPCPRMBR.TYP.equalsIgnoreCase("AITGLM")) {
            BPCPRMBR.I = 12;
        } else {
            BPCPRMBR.I = 12;
        }
        if (SCCCWA.PARM_IN_USE[BPCPRMBR.I-1] == 1) {
            BPCPRMBR.DAT_PTR = SCCCWA.PARM_PTR1_AREA[BPCPRMBR.I-1].PARM_PTR1;
        } else {
            BPCPRMBR.DAT_PTR = SCCCWA.PARM_PTR2_AREA[BPCPRMBR.I-1].PARM_PTR2;
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCPQGLM.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCPQGLM = ");
            CEP.TRC(SCCGWA, BPCPQGLM);
        }
    } //FROM #ENDIF
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
