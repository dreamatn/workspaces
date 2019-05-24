package com.hisun.CI;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class CIOT3060 {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    DBParm CITTEMP_RD;
    brParm CITTEMP_BR = new brParm();
    brParm CITDINF_BR = new brParm();
    String K_PRDP_TYP = "PRDPR";
    String K_IRUL_TYP = "TIRUL";
    String K_AP_MMO = "TD";
    String PGM_SCSSCLDT = "SCSSCLDT";
    String PGM_SCSSCKDT = "SCSSCKDT";
    String WS_MSGID = " ";
    String WS_MMO = " ";
    char WS_EC = ' ';
    char WS_PROC_TYP = ' ';
    short WS_I = 0;
    short WS_J = 0;
    short WS_L = 0;
    short WS_IDX = 0;
    CIOT3060_WS_TEXT_INFO WS_TEXT_INFO = new CIOT3060_WS_TEXT_INFO();
    char WS_DINF_FLG = ' ';
    char WS_TEMP_FLG = ' ';
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCCKDT SCCCKDT = new SCCCKDT();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCENPSW SCCENPSW = new SCCENPSW();
    SCCCLDT SCCCLDT = new SCCCLDT();
    SCCBINF SCCBINF = new SCCBINF();
    CICOVAL CICOVAL = new CICOVAL();
    CIRTYP CIRTYP = new CIRTYP();
    CIRDINF CIRDINF = new CIRDINF();
    CIRTEMP CIRTEMP = new CIRTEMP();
    SCCGWA SCCGWA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    CIB3060_AWA_3060 CIB3060_AWA_3060;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "CIOT3060 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "CIB3060_AWA_3060>");
        CIB3060_AWA_3060 = (CIB3060_AWA_3060) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.CHNL);
        B100_CHECK_INPUT();
        if (CIB3060_AWA_3060.FUNC == '0') {
            B210_ADD_CITTEMP_INFO();
        } else if (CIB3060_AWA_3060.FUNC == '3') {
            B240_QRY_XETJ_INFO();
        } else {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_FUNC_ERROR);
        }
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
    }
    public void B210_ADD_CITTEMP_INFO() throws IOException,SQLException,Exception {
        R000_DELETE_TEMP_PROC();
        WS_L = 53;
        for (WS_I = 1; WS_I <= 60 
            && CIB3060_AWA_3060.TIMES[WS_I-1].CON_TYP != 0; WS_I += 1) {
            IBS.init(SCCGWA, CIRTEMP);
            CIRTEMP.KEY.LC_NO = CIB3060_AWA_3060.LC_NO;
            CIRTEMP.KEY.SEQ = CIB3060_AWA_3060.SEQ;
            CIRTEMP.KEY.CON_SEQ = CIB3060_AWA_3060.CON_SEQ;
            CIRTEMP.KEY.SUB_SEQ = WS_I;
            if (CIRTEMP.KEY.TBL_NM == null) CIRTEMP.KEY.TBL_NM = "";
            JIBS_tmp_int = CIRTEMP.KEY.TBL_NM.length();
            for (int i=0;i<8-JIBS_tmp_int;i++) CIRTEMP.KEY.TBL_NM += " ";
            CIRTEMP.KEY.TBL_NM = "CITTEMP" + CIRTEMP.KEY.TBL_NM.substring(7);
            CIRTEMP.KEY.FUNC = '0';
            CIRTEMP.KEY.JRN_NO = SCCGWA.COMM_AREA.JRN_NO;
            CIRTEMP.KEY.AC_DATE = SCCGWA.COMM_AREA.AC_DATE;
            WS_TEXT_INFO.WS_CON_TYP = CIB3060_AWA_3060.TIMES[WS_I-1].CON_TYP;
            WS_TEXT_INFO.WS_VAL = CIB3060_AWA_3060.TIMES[WS_I-1].VAL;
            WS_TEXT_INFO.WS_VAL_COND = CIB3060_AWA_3060.TIMES[WS_I-1].VAL_COND;
            if (CIRTEMP.VAL == null) CIRTEMP.VAL = "";
            JIBS_tmp_int = CIRTEMP.VAL.length();
            for (int i=0;i<1000-JIBS_tmp_int;i++) CIRTEMP.VAL += " ";
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_TEXT_INFO);
            CIRTEMP.VAL = JIBS_tmp_str[0] + CIRTEMP.VAL.substring(WS_L);
            T000_INSERT_CITTEMP();
        }
    }
    public void B240_QRY_XETJ_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRTEMP);
        CIRTEMP.KEY.LC_NO = CIB3060_AWA_3060.LC_NO;
        CIRTEMP.KEY.SEQ = CIB3060_AWA_3060.SEQ;
        CIRTEMP.KEY.CON_SEQ = CIB3060_AWA_3060.CON_SEQ;
        if (CIRTEMP.KEY.TBL_NM == null) CIRTEMP.KEY.TBL_NM = "";
        JIBS_tmp_int = CIRTEMP.KEY.TBL_NM.length();
        for (int i=0;i<8-JIBS_tmp_int;i++) CIRTEMP.KEY.TBL_NM += " ";
        CIRTEMP.KEY.TBL_NM = "CITTEMP" + CIRTEMP.KEY.TBL_NM.substring(7);
        CIRTEMP.KEY.FUNC = '0';
        WS_I = 0;
        T000_STARTBR_CITTEMP();
        T000_READNEXT_CITTEMP();
        while (WS_TEMP_FLG != 'N') {
            WS_I += 1;
            CEP.TRC(SCCGWA, WS_I);
            CEP.TRC(SCCGWA, CIRTEMP.KEY.LC_NO);
            CEP.TRC(SCCGWA, CIRTEMP.KEY.CON_SEQ);
            R000_GEN_OUTPUT_FROM_TEMP();
            T000_READNEXT_CITTEMP();
        }
        T000_ENDBR_CITTEMP();
        if (WS_I == 0) {
            IBS.init(SCCGWA, CIRDINF);
            CIRDINF.KEY.LC_NO = CIB3060_AWA_3060.LC_NO;
            CIRDINF.KEY.LC_SEQ = CIB3060_AWA_3060.SEQ;
            CIRDINF.KEY.CON_SEQ = CIB3060_AWA_3060.CON_SEQ;
            T000_STARTBR_CITDINF();
            T000_READNEXT_CITDINF();
            while (WS_DINF_FLG != 'N') {
                WS_I += 1;
                R000_GEN_OUTPUT_FROM_DINF();
                T000_READNEXT_CITDINF();
            }
            T000_ENDBR_CITDINF();
        }
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = "CI360";
        SCCFMT.DATA_PTR = CICOVAL;
        SCCFMT.DATA_LEN = 3204;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void R000_DELETE_TEMP_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRTEMP);
        CIRTEMP.KEY.LC_NO = CIB3060_AWA_3060.LC_NO;
        CIRTEMP.KEY.SEQ = CIB3060_AWA_3060.SEQ;
        CIRTEMP.KEY.CON_SEQ = CIB3060_AWA_3060.CON_SEQ;
        if (CIRTEMP.KEY.TBL_NM == null) CIRTEMP.KEY.TBL_NM = "";
        JIBS_tmp_int = CIRTEMP.KEY.TBL_NM.length();
        for (int i=0;i<8-JIBS_tmp_int;i++) CIRTEMP.KEY.TBL_NM += " ";
        CIRTEMP.KEY.TBL_NM = "CITTEMP" + CIRTEMP.KEY.TBL_NM.substring(7);
        CIRTEMP.KEY.FUNC = '0';
        T000_DELETE_CITTEMP();
    }
    public void R000_GEN_OUTPUT_FROM_TEMP() throws IOException,SQLException,Exception {
        WS_L = 53;
        if (CIRTEMP.VAL == null) CIRTEMP.VAL = "";
        JIBS_tmp_int = CIRTEMP.VAL.length();
        for (int i=0;i<1000-JIBS_tmp_int;i++) CIRTEMP.VAL += " ";
        IBS.CPY2CLS(SCCGWA, CIRTEMP.VAL.substring(0, WS_L), WS_TEXT_INFO);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_TEXT_INFO);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], CICOVAL.TIMES[WS_I-1]);
        CICOVAL.LC_NO = CIRTEMP.KEY.LC_NO;
        CICOVAL.SEQ = CIRTEMP.KEY.SEQ;
        CICOVAL.CON_SEQ = CIRTEMP.KEY.CON_SEQ;
        CEP.TRC(SCCGWA, WS_TEXT_INFO);
        CEP.TRC(SCCGWA, CICOVAL.TIMES[WS_I-1]);
    }
    public void R000_GEN_OUTPUT_FROM_DINF() throws IOException,SQLException,Exception {
        if (CIRDINF.KEY.CON_TYP.trim().length() == 0) CICOVAL.TIMES[WS_I-1].CON_TYP = 0;
        else CICOVAL.TIMES[WS_I-1].CON_TYP = Short.parseShort(CIRDINF.KEY.CON_TYP);
        CICOVAL.TIMES[WS_I-1].VAL = CIRDINF.VAL;
        CICOVAL.TIMES[WS_I-1].VAL_COND = CIRDINF.VAL_COND;
        CICOVAL.LC_NO = CIRDINF.KEY.LC_NO;
        CICOVAL.SEQ = CIRDINF.KEY.LC_SEQ;
        CICOVAL.CON_SEQ = CIRDINF.KEY.CON_SEQ;
    }
    public void T000_INSERT_CITTEMP() throws IOException,SQLException,Exception {
        CITTEMP_RD = new DBParm();
        CITTEMP_RD.TableName = "CITTEMP";
        IBS.WRITE(SCCGWA, CIRTEMP, CITTEMP_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_INSERT_ERROR);
        }
    }
    public void T000_DELETE_CITTEMP() throws IOException,SQLException,Exception {
        CITTEMP_RD = new DBParm();
        CITTEMP_RD.TableName = "CITTEMP";
        CITTEMP_RD.where = "LC_NO = :CIRTEMP.KEY.LC_NO "
            + "AND SEQ = :CIRTEMP.KEY.SEQ "
            + "AND TBL_NM = :CIRTEMP.KEY.TBL_NM "
            + "AND FUNC = :CIRTEMP.KEY.FUNC "
            + "AND CON_SEQ = :CIRTEMP.KEY.CON_SEQ";
        IBS.DELETE(SCCGWA, CIRTEMP, this, CITTEMP_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void T000_STARTBR_CITTEMP() throws IOException,SQLException,Exception {
        CITTEMP_BR.rp = new DBParm();
        CITTEMP_BR.rp.TableName = "CITTEMP";
        CITTEMP_BR.rp.where = "LC_NO = :CIRTEMP.KEY.LC_NO "
            + "AND SEQ = :CIRTEMP.KEY.SEQ "
            + "AND CON_SEQ = :CIRTEMP.KEY.CON_SEQ "
            + "AND TBL_NM = :CIRTEMP.KEY.TBL_NM "
            + "AND FUNC = :CIRTEMP.KEY.FUNC";
        IBS.STARTBR(SCCGWA, CIRTEMP, this, CITTEMP_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void T000_READNEXT_CITTEMP() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, CIRTEMP, this, CITTEMP_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TEMP_FLG = 'N';
        }
    }
    public void T000_ENDBR_CITTEMP() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, CITTEMP_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else {
            WS_TEMP_FLG = 'N';
        }
    }
    public void T000_STARTBR_CITDINF() throws IOException,SQLException,Exception {
        CITDINF_BR.rp = new DBParm();
        CITDINF_BR.rp.TableName = "CITDINF";
        CITDINF_BR.rp.where = "LC_NO = :CIRDINF.KEY.LC_NO "
            + "AND LC_SEQ = :CIRDINF.KEY.LC_SEQ "
            + "AND CON_SEQ = :CIRDINF.KEY.CON_SEQ";
        IBS.STARTBR(SCCGWA, CIRDINF, this, CITDINF_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void T000_READNEXT_CITDINF() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, CIRDINF, this, CITDINF_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_DINF_FLG = 'N';
        }
    }
    public void T000_ENDBR_CITDINF() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, CITDINF_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else {
            WS_DINF_FLG = 'N';
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
