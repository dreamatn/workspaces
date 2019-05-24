package com.hisun.DC;

import com.hisun.BP.*;
import com.hisun.SC.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DCZSEDIQ {
    DBParm DCTCDDAT_RD;
    brParm DCTDCICT_BR = new brParm();
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String OUTPUT_FMT_9 = "DC306";
    int MAX_COL = 99;
    int MAX_ROW = 50;
    int COL_CNT = 18;
    String HIS_REMARK = "E-CASH DETAIL QUERY";
    String HIS_COPYBOOK = "DCRDCICT";
    String TBL_DCICT = "DCTDCICT";
    short PAGE_ROW = 25;
    DCZSEDIQ_WS_VARIABLES WS_VARIABLES = new DCZSEDIQ_WS_VARIABLES();
    DCZSEDIQ_WS_OUTPUT WS_OUTPUT = new DCZSEDIQ_WS_OUTPUT();
    DCZSEDIQ_WS_DB_VARS WS_DB_VARS = new DCZSEDIQ_WS_DB_VARS();
    DCZSEDIQ_WS_CONDITION_FLAG WS_CONDITION_FLAG = new DCZSEDIQ_WS_CONDITION_FLAG();
    BPCMSG_ERROR_MSG ERROR_MSG = new BPCMSG_ERROR_MSG();
    DCCMSG_ERROR_MSG ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    DCRCDDAT DCRCDDAT = new DCRCDDAT();
    DCRDCICT DCRDCICT = new DCRDCICT();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA SC_AREA;
    SCCGBPA_BP_AREA BP_AREA;
    DCCS3306 DCCS3306;
    public void MP(SCCGWA SCCGWA, DCCS3306 DCCS3306) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DCCS3306 = DCCS3306;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DCZSEDIQ return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, WS_VARIABLES);
        IBS.init(SCCGWA, WS_DB_VARS);
        IBS.init(SCCGWA, WS_OUTPUT);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_QUERY_PROC();
        if (pgmRtn) return;
    }
    public void B010_QUERY_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCCS3306.CARD_NO);
        CEP.TRC(SCCGWA, DCCS3306.TXN_TYP);
        CEP.TRC(SCCGWA, DCCS3306.STA_DATE);
        CEP.TRC(SCCGWA, DCCS3306.END_DATE);
        if (DCCS3306.CARD_NO.trim().length() == 0) {
            CEP.ERR(SCCGWA, ERROR_MSG.DC_CARD_NO_MISSING);
        }
        IBS.init(SCCGWA, DCRCDDAT);
        DCRCDDAT.KEY.CARD_NO = DCCS3306.CARD_NO;
        T000_READ_DCTCDDAT();
        if (pgmRtn) return;
        if (WS_CONDITION_FLAG.TBL_FLAG == 'N') {
            CEP.ERR(SCCGWA, ERROR_MSG.DC_CARD_NOT_EXIST);
        }
        if (DCRCDDAT.CARD_STS != 'N') {
            CEP.ERR(SCCGWA, ERROR_MSG.DC_CARD_NOT_NORMAL_STS);
        }
        IBS.init(SCCGWA, DCRDCICT);
        WS_DB_VARS.CARD_NO = DCCS3306.CARD_NO;
        if (DCCS3306.TXN_TYP == ' ') {
            WS_DB_VARS.TXN_TYP_LOW = 0X00;
            WS_DB_VARS.TXN_TYP_HI = 0XFF;
        } else {
            WS_DB_VARS.TXN_TYP_LOW = DCCS3306.TXN_TYP;
            WS_DB_VARS.TXN_TYP_HI = DCCS3306.TXN_TYP;
        }
        WS_DB_VARS.STA_DATE = DCCS3306.STA_DATE;
        WS_DB_VARS.END_DATE = DCCS3306.END_DATE;
        T000_STARTBR_DCTDCICT();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DCCS3306.CARD_NO);
        T000_READNEXT_DCTDCICT();
        if (pgmRtn) return;
        B020_01_01_OUT_TITLE();
        if (pgmRtn) return;
        WS_VARIABLES.CNT = 0;
        while (WS_CONDITION_FLAG.TBL_FLAG != 'N' 
            && SCCMPAG.FUNC != 'E') {
            B020_01_02_OUT_BRW_DATA();
            if (pgmRtn) return;
            WS_VARIABLES.CNT += 1;
            T000_READNEXT_DCTDCICT();
            if (pgmRtn) return;
        }
        T000_ENDBR_DCTDCICT();
        if (pgmRtn) return;
    }
    public void B020_01_01_OUT_TITLE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 93;
        SCCMPAG.SCR_ROW_CNT = (short) MAX_ROW;
        SCCMPAG.SCR_COL_CNT = (short) COL_CNT;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B020_01_02_OUT_BRW_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_OUTPUT);
        WS_OUTPUT.CARD_NO = DCRDCICT.CARD_NO;
        WS_OUTPUT.TXN_DT = DCRDCICT.KEY.TXN_DT;
        WS_OUTPUT.TXN_TYP = DCRDCICT.TXN_TYP;
        WS_OUTPUT.TXN_AMT = DCRDCICT.TXN_AMT;
        WS_OUTPUT.BEF_TXN_AMT = DCRDCICT.BEF_TXN_AMT;
        WS_OUTPUT.TXN_CHNL_NO = DCRDCICT.TXN_CHNL_NO;
        WS_OUTPUT.TXN_STS = DCRDCICT.TXN_STS;
        WS_OUTPUT.WRITE_CARD_STS = DCRDCICT.WRITE_CARD_STS;
        WS_OUTPUT.TXN_JANNO = DCRDCICT.KEY.TXN_JANNO;
        WS_OUTPUT.TXN_BR = DCRDCICT.TXN_BR;
        WS_OUTPUT.TXN_TLR = DCRDCICT.TXN_TLR;
        CEP.TRC(SCCGWA, WS_OUTPUT.CARD_NO);
        CEP.TRC(SCCGWA, WS_OUTPUT.TXN_DT);
        CEP.TRC(SCCGWA, WS_OUTPUT.TXN_TYP);
        CEP.TRC(SCCGWA, "====TEST=====");
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_OUTPUT);
        SCCMPAG.DATA_LEN = 93;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_VARIABLES.ERR_MSG);
    }
    public void T000_READ_DCTCDDAT() throws IOException,SQLException,Exception {
        DCTCDDAT_RD = new DBParm();
        DCTCDDAT_RD.TableName = "DCTCDDAT";
        IBS.READ(SCCGWA, DCRCDDAT, DCTCDDAT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_CONDITION_FLAG.TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_CONDITION_FLAG.TBL_FLAG = 'N';
        }
    }
    public void T000_STARTBR_DCTDCICT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_DB_VARS.CARD_NO);
        CEP.TRC(SCCGWA, WS_DB_VARS.TXN_TYP_LOW);
        CEP.TRC(SCCGWA, WS_DB_VARS.TXN_TYP_HI);
        CEP.TRC(SCCGWA, WS_DB_VARS.STA_DATE);
        CEP.TRC(SCCGWA, WS_DB_VARS.END_DATE);
        DCTDCICT_BR.rp = new DBParm();
        DCTDCICT_BR.rp.TableName = "DCTDCICT";
        DCTDCICT_BR.rp.where = "CARD_NO = :WS_DB_VARS.CARD_NO "
            + "AND ( TXN_TYP >= :WS_DB_VARS.TXN_TYP_LOW ) "
            + "AND ( TXN_TYP <= :WS_DB_VARS.TXN_TYP_HI ) "
            + "AND ( TXN_DT >= :WS_DB_VARS.STA_DATE ) "
            + "AND ( TXN_DT <= :WS_DB_VARS.END_DATE )";
        DCTDCICT_BR.rp.order = "TS , TXN_TYP DESC";
        IBS.STARTBR(SCCGWA, DCRDCICT, this, DCTDCICT_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_CONDITION_FLAG.TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_CONDITION_FLAG.TBL_FLAG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = TBL_DCICT;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "STARTBR";
            B_DB_EXCP();
            if (pgmRtn) return;
    }
    public void T000_READNEXT_DCTDCICT() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, DCRDCICT, this, DCTDCICT_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_CONDITION_FLAG.TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_CONDITION_FLAG.TBL_FLAG = 'N';
        }
    }
    public void T000_ENDBR_DCTDCICT() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, DCTDCICT_BR);
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
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
