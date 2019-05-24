package com.hisun.TD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;
import com.hisun.CI.*;
import com.hisun.BP.*;

import java.io.IOException;
import java.sql.SQLException;

public class TDOT1910 {
    String JIBS_tmp_str[] = new String[10];
    DBParm TDTSMST_RD;
    brParm TDTSMST_BR = new brParm();
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    int WS_CNT = 0;
    char WS_SMST_FLAG = ' ';
    TDCMSG_ERROR_MSG TDCMSG_ERROR_MSG = new TDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    TDCUGRP TDCUGRP = new TDCUGRP();
    CICQACRL CICQACRL = new CICQACRL();
    TDRSMST TDRSMST = new TDRSMST();
    BPCSOCAC BPCSOCAC = new BPCSOCAC();
    CICSACAC CICSACAC = new CICSACAC();
    SCCGWA SCCGWA;
    TDB1910_AWA_1910 TDB1910_AWA_1910;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "TDOT1910 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "TDB1910_AWA_1910>");
        TDB1910_AWA_1910 = (TDB1910_AWA_1910) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B015_CHECK_BAL();
        if (TDRSMST.BAL > 0) {
            B020_LINK_TDZUGRP_PROC();
        } else {
            B020_SMST_XH();
        }
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
    }
    public void B015_CHECK_BAL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDRSMST);
        TDRSMST.AC_NO = TDB1910_AWA_1910.AC_NO;
        T000_READ_SMST_MR();
    }
    public void B020_SMST_XH() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDRSMST);
        TDRSMST.AC_NO = TDB1910_AWA_1910.AC_NO;
        T000_STARTBR_TDTSMST();
        T000_READNEXT_TDTSMST();
        while (WS_SMST_FLAG != 'N') {
            if (TDRSMST.BAL == 0) {
                TDRSMST.ACO_STS = '1';
                T000_REWRITE_SMST();
                B250_GEN_CI_AC_INF();
            }
            T000_READNEXT_TDTSMST();
        }
        T000_ENDBR_TDTSMST();
    }
    public void B020_LINK_TDZUGRP_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDCUGRP);
        IBS.init(SCCGWA, CICQACRL);
        TDCUGRP.AC_NO = TDB1910_AWA_1910.AC_NO;
        TDCUGRP.PROD_CD = TDB1910_AWA_1910.PROD_CD;
        TDCUGRP.DRAW_MTH = TDB1910_AWA_1910.DRAW_MTH;
        TDCUGRP.PSW = TDB1910_AWA_1910.PSW;
        TDCUGRP.ID_TYP = TDB1910_AWA_1910.ID_TYP;
        TDCUGRP.ID_NO = TDB1910_AWA_1910.ID_NO;
        TDCUGRP.DRAW_TYP = TDB1910_AWA_1910.DRAW_TYP;
        TDCUGRP.TXN_AMT = TDB1910_AWA_1910.TXN_AMT;
        TDCUGRP.GT_FLG = TDB1910_AWA_1910.GT_FLG;
        CICQACRL.FUNC = 'I';
        CICQACRL.DATA.AC_NO = TDB1910_AWA_1910.AC_NO;
        CEP.TRC(SCCGWA, CICQACRL.DATA.AC_NO);
        CICQACRL.DATA.AC_REL = "06";
        S000_CALL_CIZQACRL();
        TDCUGRP.OP_AC = CICQACRL.O_DATA.O_REL_AC_NO;
        TDCUGRP.OPTION = "XXTZC";
        S000_CALL_TDZUGRP();
    }
    public void B250_GEN_CI_AC_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICSACAC);
        CICSACAC.FUNC = 'D';
        CICSACAC.DATA.PROD_CD = TDRSMST.PROD_CD;
        CICSACAC.DATA.ACAC_NO = TDRSMST.KEY.ACO_AC;
        CICSACAC.DATA.AGR_NO = TDRSMST.AC_NO;
        CICSACAC.DATA.CCY = "156";
        CICSACAC.DATA.CR_FLG = '1';
        CICSACAC.DATA.FRM_APP = "TD";
        CICSACAC.DATA.OPN_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        CICSACAC.DATA.OPEN_DT = SCCGWA.COMM_AREA.AC_DATE;
        S000_CALL_CIZSACAC();
    }
    public void B630_WRT_BPTOCAC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDRSMST.KEY.ACO_AC);
        CEP.TRC(SCCGWA, TDRSMST.AC_NO);
        IBS.init(SCCGWA, BPCSOCAC);
        BPCSOCAC.FUNC = 'U';
        BPCSOCAC.AC = TDRSMST.AC_NO;
        BPCSOCAC.ACO_AC = TDRSMST.KEY.ACO_AC;
        BPCSOCAC.STS = 'C';
        BPCSOCAC.WORK_TYP = "12";
        BPCSOCAC.CI_TYPE = '1';
        BPCSOCAC.SEQ = 1;
        BPCSOCAC.CCY = "156";
        BPCSOCAC.CCY_TYPE = '1';
        BPCSOCAC.AUT_TLR = " ";
        BPCSOCAC.CLOSE_DATE = TDRSMST.CLO_DATE;
        BPCSOCAC.CLOSE_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCSOCAC.OPEN_DATE = TDRSMST.OPEN_DATE;
        BPCSOCAC.OPEN_TLR = TDRSMST.CRT_TLR;
        BPCSOCAC.CHNL_NO = SCCGWA.COMM_AREA.CHNL;
        BPCSOCAC.OPEN_AMT = TDRSMST.BAL;
        BPCSOCAC.PROD_CD = TDRSMST.PROD_CD;
        BPCSOCAC.BR = TDRSMST.OWNER_BR;
        CEP.TRC(SCCGWA, TDRSMST.BAL);
        BPCSOCAC.LOSS_INT = 0;
        BPCSOCAC.LOSS_TAX = 0;
        BPCSOCAC.LOSS_AMT = 0;
        S000_CALL_BPZSOCAC();
    }
    public void S000_CALL_CIZSACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-MAIN-CIZSACAC", CICSACAC);
        if (CICSACAC.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICSACAC.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void T000_REWRITE_SMST() throws IOException,SQLException,Exception {
        TDRSMST.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        TDRSMST.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
        TDRSMST.UPD_TIME = SCCGWA.COMM_AREA.TR_TIME;
        TDRSMST.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDRSMST.CLO_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDTSMST_RD = new DBParm();
        TDTSMST_RD.TableName = "TDTSMST";
        IBS.REWRITE(SCCGWA, TDRSMST, TDTSMST_RD);
    }
    public void S000_CALL_BPZSOCAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-S-MAINT-OCAC-INFO", BPCSOCAC);
        if (BPCSOCAC.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, BPCSOCAC.RC.RC_CODE);
        }
    }
    public void T000_STARTBR_TDTSMST() throws IOException,SQLException,Exception {
        WS_SMST_FLAG = 'N';
        TDTSMST_BR.rp = new DBParm();
        TDTSMST_BR.rp.TableName = "TDTSMST";
        TDTSMST_BR.rp.upd = true;
        TDTSMST_BR.rp.where = "AC_NO = :TDRSMST.AC_NO "
            + "AND ACO_STS = '0' "
            + "AND SUBSTR ( STSW , 52 , 1 ) < > '1'";
        TDTSMST_BR.rp.order = "VAL_DATE DESC";
        IBS.STARTBR(SCCGWA, TDRSMST, this, TDTSMST_BR);
    }
    public void T000_READNEXT_TDTSMST() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, TDRSMST, this, TDTSMST_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_SMST_FLAG = 'F';
        } else {
            WS_SMST_FLAG = 'N';
        }
    }
    public void T000_READ_SMST_MR() throws IOException,SQLException,Exception {
        TDTSMST_RD = new DBParm();
        TDTSMST_RD.TableName = "TDTSMST";
        TDTSMST_RD.where = "AC_NO = :TDRSMST.AC_NO "
            + "AND SUBSTR ( STSW , 52 , 1 ) = '1'";
        TDTSMST_RD.fst = true;
        IBS.READ(SCCGWA, TDRSMST, this, TDTSMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_AC_NOT_EXIST);
        }
    }
    public void T000_ENDBR_TDTSMST() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, TDTSMST_BR);
    }
    public void S000_CALL_CIZQACRL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACRL", CICQACRL);
        if (CICQACRL.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICQACRL.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_TDZUGRP() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "TD-UNIT-GROUP", TDCUGRP);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
