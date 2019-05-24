package com.hisun.BP;

import com.hisun.SC.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPZFCREB {
    DBParm BPTFECRE_RD;
    brParm BPTFECRE_BR = new brParm();
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    int K_MAX_ROW = 18;
    int K_MAX_COL = 500;
    String K_OUTPUT_FMT_9 = "BP059";
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    BPRLOSS BPRLOSS = new BPRLOSS();
    BPRFECRE BPRFECRE = new BPRFECRE();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCOFCRE BPCOFCRE;
    public void MP(SCCGWA SCCGWA, BPCOFCRE BPCOFCRE) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCOFCRE = BPCOFCRE;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZFCREB return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPCOFCRE.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B020_MAIN_PROC();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
    }
    public void B020_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCOFCRE.FUNC);
        if (BPCOFCRE.FUNC == 'I') {
            B020_INQUERY_PROC();
            if (pgmRtn) return;
        } else if (BPCOFCRE.FUNC == 'B') {
            B020_BROWSE_PROC();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_FUNC_ERROR, BPCOFCRE.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B020_INQUERY_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRFECRE);
        T000_READ_BPTFECRE();
        if (pgmRtn) return;
        if (BPCOFCRE.RETURN_INFO == 'F') {
            B060_01_DATA_TRANS_TO_FMT();
            if (pgmRtn) return;
            B060_02_DATA_OUTPUT_FMT();
            if (pgmRtn) return;
        } else {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_OCAC_NOTFND);
        }
    }
    public void B020_BROWSE_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRFECRE);
        T000_STARTBR_BPTFECRE();
        if (pgmRtn) return;
        T000_READNEXT_BPTFECRE();
        if (pgmRtn) return;
        if (BPCOFCRE.RETURN_INFO == 'F') {
            B060_01_OUT_TITLE();
            if (pgmRtn) return;
        }
        while (BPCOFCRE.RETURN_INFO != 'N' 
            && SCCMPAG.FUNC != 'E') {
            B060_02_OUT_BRW_DATA();
            if (pgmRtn) return;
            T000_READNEXT_BPTFECRE();
            if (pgmRtn) return;
        }
        T000_ENDBR_BPTFECRE();
        if (pgmRtn) return;
    }
    public void B060_01_OUT_TITLE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 481;
        SCCMPAG.MAX_COL_NO = (short) K_MAX_COL;
        SCCMPAG.SCR_ROW_CNT = (short) K_MAX_ROW;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B060_01_DATA_TRANS_TO_FMT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOFCRE.DATA_INFO);
        BPCOFCRE.DATA_INFO.REB_CODE = BPRFECRE.KEY.REB_CODE;
        BPCOFCRE.DATA_INFO.REB_DESC = BPRFECRE.REB_DESC;
        BPCOFCRE.DATA_INFO.EFF_DATE = BPRFECRE.EFF_DATE;
        BPCOFCRE.DATA_INFO.EXP_DATE = BPRFECRE.EXP_DATE;
        BPCOFCRE.DATA_INFO.TX_MMO = BPRFECRE.TX_MMO;
        BPCOFCRE.DATA_INFO.R_CYCLE = BPRFECRE.REB_CYCLE;
        BPCOFCRE.DATA_INFO.PER_CNT = BPRFECRE.PERIDO_CNT;
        BPCOFCRE.DATA_INFO.REB_STDT = BPRFECRE.REB_STDT;
        BPCOFCRE.DATA_INFO.REB_DATE = BPRFECRE.REB_DATE;
        BPCOFCRE.DATA_INFO.L_REB_DT = BPRFECRE.LAST_REB_DATE;
        BPCOFCRE.DATA_INFO.N_REB_DT = BPRFECRE.NEXT_REB_DATE;
        BPCOFCRE.DATA_INFO.REB_TYPE = BPRFECRE.REB_TYPE;
        BPCOFCRE.DATA_INFO.AGG_TYPE = BPRFECRE.AGGR_TYPE;
        BPCOFCRE.DATA_INFO.UP_AMT1 = BPRFECRE.UP_AMT_1;
        CEP.TRC(SCCGWA, BPRFECRE.UP_AMT_1);
        CEP.TRC(SCCGWA, BPCOFCRE.DATA_INFO.UP_AMT1);
        CEP.TRC(SCCGWA, "999999999999.99");
        BPCOFCRE.DATA_INFO.UP_CNT1 = BPRFECRE.UP_CNT_1;
        BPCOFCRE.DATA_INFO.UP_D_AMT1 = BPRFECRE.UP_DAY_AMT1;
        BPCOFCRE.DATA_INFO.AGG_MTH1 = BPRFECRE.AGG_MTH1;
        BPCOFCRE.DATA_INFO.FEE_AMT1 = BPRFECRE.FEE_AMT_1;
        BPCOFCRE.DATA_INFO.FEE_PER1 = BPRFECRE.FEE_PER_1;
        BPCOFCRE.DATA_INFO.UP_AMT2 = BPRFECRE.UP_AMT_2;
        BPCOFCRE.DATA_INFO.UP_CNT2 = BPRFECRE.UP_CNT_2;
        BPCOFCRE.DATA_INFO.UP_D_AMT2 = BPRFECRE.UP_DAY_AMT2;
        BPCOFCRE.DATA_INFO.AGG_MTH2 = BPRFECRE.AGG_MTH2;
        BPCOFCRE.DATA_INFO.FEE_AMT2 = BPRFECRE.FEE_AMT_2;
        BPCOFCRE.DATA_INFO.FEE_PER2 = BPRFECRE.FEE_PER_2;
        BPCOFCRE.DATA_INFO.UP_AMT3 = BPRFECRE.UP_AMT_3;
        BPCOFCRE.DATA_INFO.UP_CNT3 = BPRFECRE.UP_CNT_3;
        BPCOFCRE.DATA_INFO.UP_D_AMT3 = BPRFECRE.UP_DAY_AMT3;
        BPCOFCRE.DATA_INFO.AGG_MTH3 = BPRFECRE.AGG_MTH3;
        BPCOFCRE.DATA_INFO.FEE_AMT3 = BPRFECRE.FEE_AMT_3;
        BPCOFCRE.DATA_INFO.FEE_PER3 = BPRFECRE.FEE_PER_3;
        BPCOFCRE.DATA_INFO.UP_AMT4 = BPRFECRE.UP_AMT_4;
        BPCOFCRE.DATA_INFO.UP_CNT4 = BPRFECRE.UP_CNT_4;
        BPCOFCRE.DATA_INFO.UP_D_AMT4 = BPRFECRE.UP_DAY_AMT4;
        BPCOFCRE.DATA_INFO.AGG_MTH4 = BPRFECRE.AGG_MTH4;
        BPCOFCRE.DATA_INFO.FEE_AMT4 = BPRFECRE.FEE_AMT_4;
        BPCOFCRE.DATA_INFO.FEE_PER4 = BPRFECRE.FEE_PER_4;
        BPCOFCRE.DATA_INFO.UP_AMT5 = BPRFECRE.UP_AMT_5;
        BPCOFCRE.DATA_INFO.UP_CNT5 = BPRFECRE.UP_CNT_5;
        BPCOFCRE.DATA_INFO.UP_D_AMT5 = BPRFECRE.UP_DAY_AMT5;
        BPCOFCRE.DATA_INFO.AGG_MTH5 = BPRFECRE.AGG_MTH5;
        BPCOFCRE.DATA_INFO.FEE_AMT5 = BPRFECRE.FEE_AMT_5;
        BPCOFCRE.DATA_INFO.FEE_PER5 = BPRFECRE.FEE_PER_5;
    }
    public void B060_02_OUT_BRW_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOFCRE.DATA_INFO);
        BPCOFCRE.DATA_INFO.REB_CODE = BPRFECRE.KEY.REB_CODE;
        BPCOFCRE.DATA_INFO.REB_DESC = BPRFECRE.REB_DESC;
        BPCOFCRE.DATA_INFO.EFF_DATE = BPRFECRE.EFF_DATE;
        BPCOFCRE.DATA_INFO.EXP_DATE = BPRFECRE.EXP_DATE;
        BPCOFCRE.DATA_INFO.TX_MMO = BPRFECRE.TX_MMO;
        BPCOFCRE.DATA_INFO.R_CYCLE = BPRFECRE.REB_CYCLE;
        BPCOFCRE.DATA_INFO.PER_CNT = BPRFECRE.PERIDO_CNT;
        BPCOFCRE.DATA_INFO.REB_STDT = BPRFECRE.REB_STDT;
        BPCOFCRE.DATA_INFO.REB_DATE = BPRFECRE.REB_DATE;
        BPCOFCRE.DATA_INFO.L_REB_DT = BPRFECRE.LAST_REB_DATE;
        BPCOFCRE.DATA_INFO.N_REB_DT = BPRFECRE.NEXT_REB_DATE;
        BPCOFCRE.DATA_INFO.REB_TYPE = BPRFECRE.REB_TYPE;
        BPCOFCRE.DATA_INFO.AGG_TYPE = BPRFECRE.AGGR_TYPE;
        CEP.TRC(SCCGWA, BPRFECRE.KEY.REB_CODE);
        CEP.TRC(SCCGWA, BPCOFCRE.DATA_INFO.REB_CODE);
        CEP.TRC(SCCGWA, BPRFECRE.EFF_DATE);
        CEP.TRC(SCCGWA, BPCOFCRE.DATA_INFO.EFF_DATE);
        CEP.TRC(SCCGWA, BPRFECRE.EXP_DATE);
        CEP.TRC(SCCGWA, BPCOFCRE.DATA_INFO.EXP_DATE);
        CEP.TRC(SCCGWA, BPRFECRE.TX_MMO);
        CEP.TRC(SCCGWA, BPCOFCRE.DATA_INFO.TX_MMO);
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, BPCOFCRE.DATA_INFO);
        SCCMPAG.DATA_LEN = 481;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B060_02_DATA_OUTPUT_FMT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT_9;
        SCCFMT.DATA_PTR = BPCOFCRE.DATA_INFO;
        SCCFMT.DATA_LEN = 481;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void T000_READ_BPTFECRE() throws IOException,SQLException,Exception {
        BPRFECRE.KEY.REB_CODE = BPCOFCRE.DATA_INFO.REB_CODE;
        BPTFECRE_RD = new DBParm();
        BPTFECRE_RD.TableName = "BPTFECRE";
        BPTFECRE_RD.where = "REB_CODE = :BPRFECRE.KEY.REB_CODE";
        IBS.READ(SCCGWA, BPRFECRE, this, BPTFECRE_RD);
        CEP.TRC(SCCGWA, BPCOFCRE.RC);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCOFCRE.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCOFCRE.RETURN_INFO = 'N';
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_OCAC_NOTFND, BPCOFCRE.RC);
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "BPTFECRE";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_BPTFECRE() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "START BROWER");
        BPRFECRE.KEY.REB_CODE = BPCOFCRE.DATA_INFO.REB_CODE;
        BPTFECRE_BR.rp = new DBParm();
        BPTFECRE_BR.rp.TableName = "BPTFECRE";
        BPTFECRE_BR.rp.where = "REB_CODE >= :BPRFECRE.KEY.REB_CODE";
        IBS.STARTBR(SCCGWA, BPRFECRE, this, BPTFECRE_BR);
    }
    public void T000_READNEXT_BPTFECRE() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "NEXT BROWER");
        BPTFECRE_BR.rp.errhdl = true;
        IBS.READNEXT(SCCGWA, BPRFECRE, this, BPTFECRE_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCOFCRE.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCOFCRE.RETURN_INFO = 'N';
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_OCAC_NOTFND, BPCOFCRE.RC);
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "BPTFECRE";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READNEXT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_ENDBR_BPTFECRE() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTFECRE_BR);
    }
    public void B_MPAG() throws IOException,SQLException,Exception {
    if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF ONL
        JIBS_tmp_str[9] = "SCZMPAG";
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[9], SCCEXCP.MSG_TEXT.MSG_TEXT_C);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[9], SCCEXCP.MSG_TEXT.MSG_TEXT_D);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[9], SCCEXCP.MSG_TEXT.MSG_TEXT_Q);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[9], SCCEXCP.MSG_TEXT.MSG_TEXT_M);
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
        if (BPCOFCRE.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCPOACAC = ");
            CEP.TRC(SCCGWA, BPCOFCRE);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
