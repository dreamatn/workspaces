package com.hisun.FS;

import com.hisun.GD.*;
import com.hisun.DD.*;
import com.hisun.TD.*;
import com.hisun.SC.*;
import com.hisun.SC.SCCBINF;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;
import com.hisun.CI.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class FSZQGRP {
    DBParm FSTMST_RD;
    brParm FSTMST_BR = new brParm();
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    int K_MAX_ROW = 5;
    int K_MAX_COL = 0;
    int K_COL_CNT = 0;
    String WS_ERR_MSG = " ";
    String WS_AC = " ";
    String WS_GROUP_NO = " ";
    int WS_I = 0;
    String WS_UPACC_NO = " ";
    String WS_ACC_NO = " ";
    FSZQGRP_WS_OUT_RECODE WS_OUT_RECODE = new FSZQGRP_WS_OUT_RECODE();
    FSZQGRP_WS_CIAC_D WS_CIAC_D = new FSZQGRP_WS_CIAC_D();
    char WS_MST_FLAG = ' ';
    char WS_STAC_FLAG = ' ';
    GDCMSG_ERROR_MSG GDCMSG_ERROR_MSG = new GDCMSG_ERROR_MSG();
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    TDCMSG_ERROR_MSG TDCMSG_ERROR_MSG = new TDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCBINF SCCBINF = new SCCBINF();
    SCCMPAG SCCMPAG = new SCCMPAG();
    CICSOEC CICSOEC = new CICSOEC();
    CICQCIAC CICQCIAC = new CICQCIAC();
    CICQACAC CICQACAC = new CICQACAC();
    CICQACRI CICQACRI = new CICQACRI();
    FSCOQGRP FSCOQGRP = new FSCOQGRP();
    GDRSTAC GDRSTAC = new GDRSTAC();
    FSRMST FSRMST = new FSRMST();
    SCCGWA SCCGWA;
    FSCQGRP FSCQGRP;
    public void MP(SCCGWA SCCGWA, FSCQGRP FSCQGRP) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.FSCQGRP = FSCQGRP;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "FSZQGRP return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        R000_01_OUT_TITLE();
        if (pgmRtn) return;
        B020_SEL_STS();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, FSRMST);
        WS_STAC_FLAG = ' ';
        CEP.TRC(SCCGWA, FSCQGRP.TOP_AC);
        if (FSCQGRP.TOP_AC.trim().length() > 0) {
            T000_READ_FSTMST();
            if (pgmRtn) return;
            if (WS_MST_FLAG == 'N') {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_NOT_GROUP_AC;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            } else {
                WS_AC = FSCQGRP.TOP_AC;
            }
        }
    }
    public void B020_SEL_STS() throws IOException,SQLException,Exception {
        WS_STAC_FLAG = ' ';
        IBS.init(SCCGWA, FSRMST);
        CEP.TRC(SCCGWA, "11111");
        FSRMST.KEY.ACC_NO = WS_AC;
        CEP.TRC(SCCGWA, "2222");
        WS_STAC_FLAG = 'F';
        T000_STARTBR_FSTMST_TWO();
        if (pgmRtn) return;
        T000_READ_FSTMST_TWO();
        if (pgmRtn) return;
        while (WS_STAC_FLAG != 'N') {
            CEP.TRC(SCCGWA, "3333");
            IBS.init(SCCGWA, FSCOQGRP);
            CEP.TRC(SCCGWA, FSRMST.KEY.ACC_NO);
            WS_OUT_RECODE.WS_GRP_NO = FSRMST.GROUP_NO;
            WS_OUT_RECODE.WS_UPAC = FSRMST.UPACC_NO;
            WS_OUT_RECODE.WS_AC_A = FSRMST.KEY.ACC_NO;
            WS_OUT_RECODE.WS_SS_AMT = FSRMST.SS_BAL;
            WS_OUT_RECODE.WS_XH_AMT = FSRMST.XH_BAL;
            WS_OUT_RECODE.WS_KEEP_AMT = FSRMST.KEEP_AMT;
            FSRMST.KEY.ACC_NO = WS_OUT_RECODE.WS_UPAC;
            R100_OUT_PROC();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_UPAC);
            CEP.TRC(SCCGWA, FSRMST.KEY.ACC_NO);
            T000_READ_FSTMST_TWO();
            if (pgmRtn) return;
        }
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
    public void R100_OUT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_OUT_RECODE);
        SCCMPAG.DATA_LEN = 124;
        CEP.TRC(SCCGWA, SCCMPAG.DATA_LEN);
        B_MPAG();
        if (pgmRtn) return;
    }
    public void T000_READ_FSTMST() throws IOException,SQLException,Exception {
        FSTMST_RD = new DBParm();
        FSTMST_RD.TableName = "FSTMST";
        FSTMST_RD.where = "ACC_NO = :FSCQGRP.TOP_AC";
        IBS.READ(SCCGWA, FSRMST, this, FSTMST_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_MST_FLAG = 'F';
        } else {
            WS_MST_FLAG = 'N';
        }
    }
    public void T000_STARTBR_FSTMST_TWO() throws IOException,SQLException,Exception {
        FSTMST_BR.rp = new DBParm();
        FSTMST_BR.rp.TableName = "FSTMST";
        FSTMST_BR.rp.where = "ACC_NO = :FSRMST.KEY.ACC_NO";
        FSTMST_BR.rp.order = "GROUP_NO";
        IBS.STARTBR(SCCGWA, FSRMST, this, FSTMST_BR);
    }
    public void T000_READ_FSTMST_TWO() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, FSRMST, this, FSTMST_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_STAC_FLAG = 'F';
        } else {
            WS_STAC_FLAG = 'N';
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
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
