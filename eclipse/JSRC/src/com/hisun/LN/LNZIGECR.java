package com.hisun.LN;

import com.hisun.SC.*;
import com.hisun.BP.*;

import java.io.IOException;
import java.sql.SQLException;

public class LNZIGECR {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    DBParm LNTAGRE_RD;
    boolean pgmRtn = false;
    String K_NEXT_SEQ = "00000001";
    char WS_READ_LNTCMMT_FLG = ' ';
    char WS_TMP_PARTI_ROLE = ' ';
    String WS_TEMP_CMMT_NO = " ";
    String WS_DRAW_NO = " ";
    String WS_DRAW_NO1 = " ";
    LNZIGECR_WS_MSGID WS_MSGID = new LNZIGECR_WS_MSGID();
    short WS_FLD_NO = 0;
    char WS_CMMT_FLG = ' ';
    LNRAGRE LNRAGRE = new LNRAGRE();
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCCGAC BPCCGAC = new BPCCGAC();
    SCCGWA SCCGWA;
    LNCIGECR LNCIGECR;
    public void MP(SCCGWA SCCGWA, LNCIGECR LNCIGECR) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCIGECR = LNCIGECR;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZIGECR return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        LNCIGECR.RC.RC_MMO = "LN";
        LNCIGECR.RC.RC_CODE = 0;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_PROC();
        if (pgmRtn) return;
        B030_GENERATE_CNTR_PROC();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_PROC() throws IOException,SQLException,Exception {
        if (LNCIGECR.INPUT_INFO.DRAW_NO.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_E_INPUT_PAPER_NO, LNCIGECR.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (LNCIGECR.INPUT_INFO.CI_NO.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_CINO_MUST_INPUT, LNCIGECR.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B030_GENERATE_CNTR_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNCIGECR.INPUT_INFO.PAPER_NO);
        IBS.init(SCCGWA, LNRAGRE);
        LNRAGRE.DRAW_NO = LNCIGECR.INPUT_INFO.DRAW_NO;
        LNRAGRE.PAPER_NO = LNCIGECR.INPUT_INFO.PAPER_NO;
        LNRAGRE.CTA_FROM = LNCIGECR.INPUT_INFO.CTA_FROM;
        T000_READ_AGRE();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, LNRAGRE.KEY.CONTRACT_NO);
        CEP.TRC(SCCGWA, WS_CMMT_FLG);
        if (WS_CMMT_FLG == 'N') {
            IBS.init(SCCGWA, BPCCGAC);
            BPCCGAC.DATA.ACO_AC_FLG = '8';
            BPCCGAC.DATA.ACO_AC_MMO = "13";
            CEP.TRC(SCCGWA, LNCIGECR.INPUT_INFO.PROD_TYP);
            CEP.TRC(SCCGWA, "2222222");
            if (LNCIGECR.INPUT_INFO.PROD_TYP.trim().length() > 0) {
                if (LNCIGECR.INPUT_INFO.PROD_TYP == null) LNCIGECR.INPUT_INFO.PROD_TYP = "";
                JIBS_tmp_int = LNCIGECR.INPUT_INFO.PROD_TYP.length();
                for (int i=0;i<4-JIBS_tmp_int;i++) LNCIGECR.INPUT_INFO.PROD_TYP += " ";
                if (LNCIGECR.INPUT_INFO.PROD_TYP.substring(2 - 1, 2 + 3 - 1).trim().length() == 0) BPCCGAC.DATA.ACO_AC_DEF = 0;
                else BPCCGAC.DATA.ACO_AC_DEF = Short.parseShort(LNCIGECR.INPUT_INFO.PROD_TYP.substring(2 - 1, 2 + 3 - 1));
            } else {
                BPCCGAC.DATA.ACO_AC_DEF = 0;
            }
            BPCCGAC.DATA.AC_KIND = '2';
            S000_CALL_BPZGACNO();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, BPCCGAC.DATA.AC_NO);
            WS_TEMP_CMMT_NO = BPCCGAC.DATA.ACO_AC;
            WS_DRAW_NO = K_NEXT_SEQ;
            B031_RECORD_INFO_PROC();
            if (pgmRtn) return;
        } else {
            if (LNCIGECR.INPUT_INFO.CTA_FROM == '1') {
                IBS.init(SCCGWA, BPCCGAC);
                BPCCGAC.DATA.AC_KIND = '2';
                BPCCGAC.DATA.CI_NO = LNCIGECR.INPUT_INFO.CI_NO;
                BPCCGAC.DATA.BR = LNCIGECR.INPUT_INFO.BOOK_BR;
                BPCCGAC.DATA.EQ_CCY = LNCIGECR.INPUT_INFO.CCY;
                BPCCGAC.DATA.ACO_AC_FLG = '8';
                BPCCGAC.DATA.ACO_AC_MMO = "13";
                CEP.TRC(SCCGWA, LNCIGECR.INPUT_INFO.PROD_TYP);
                CEP.TRC(SCCGWA, "1111111");
                if (LNCIGECR.INPUT_INFO.PROD_TYP.trim().length() > 0) {
                    if (LNCIGECR.INPUT_INFO.PROD_TYP == null) LNCIGECR.INPUT_INFO.PROD_TYP = "";
                    JIBS_tmp_int = LNCIGECR.INPUT_INFO.PROD_TYP.length();
                    for (int i=0;i<4-JIBS_tmp_int;i++) LNCIGECR.INPUT_INFO.PROD_TYP += " ";
                    if (LNCIGECR.INPUT_INFO.PROD_TYP.substring(2 - 1, 2 + 3 - 1).trim().length() == 0) BPCCGAC.DATA.ACO_AC_DEF = 0;
                    else BPCCGAC.DATA.ACO_AC_DEF = Short.parseShort(LNCIGECR.INPUT_INFO.PROD_TYP.substring(2 - 1, 2 + 3 - 1));
                } else {
                    BPCCGAC.DATA.ACO_AC_DEF = 0;
                }
                S000_CALL_BPZGACNO();
                if (pgmRtn) return;
                WS_TEMP_CMMT_NO = BPCCGAC.DATA.ACO_AC;
                LNCIGECR.INPUT_INFO.PAPER_NO = " ";
                LNCIGECR.INPUT_INFO.DRAW_NO = " ";
                B031_RECORD_INFO_PROC();
                if (pgmRtn) return;
            } else {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_DRAW_DUPKEY, LNCIGECR.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
        CEP.TRC(SCCGWA, WS_TEMP_CMMT_NO);
        CEP.TRC(SCCGWA, WS_DRAW_NO);
        LNCIGECR.OUTPOUT_INFO.CONTRACT_NO = WS_TEMP_CMMT_NO;
        LNCIGECR.OUTPOUT_INFO.VRTU_FLG = LNRAGRE.VRTU_FLG;
        LNCIGECR.OUTPOUT_INFO.CMMT_NO = WS_TEMP_CMMT_NO;
    }
    public void B032_GENERATE_CNTR_PROC() throws IOException,SQLException,Exception {
    }
    public void B031_RECORD_INFO_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRAGRE);
        LNRAGRE.KEY.CONTRACT_NO = WS_TEMP_CMMT_NO;
        LNRAGRE.DRAW_NO = LNCIGECR.INPUT_INFO.DRAW_NO;
        LNRAGRE.PAPER_NO = LNCIGECR.INPUT_INFO.PAPER_NO;
        LNRAGRE.CTA_FROM = LNCIGECR.INPUT_INFO.CTA_FROM;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        if (JIBS_tmp_str[0].equalsIgnoreCase("0132151")) {
            LNRAGRE.PARTI_ROLE = 'A';
        } else {
            LNRAGRE.PARTI_ROLE = 'B';
        }
        LNRAGRE.VRTU_FLG = 'Y';
        LNRAGRE.REL_CTA_NO = LNCIGECR.INPUT_INFO.O_CNT_NO;
        LNRAGRE.CNTR_ATTR = LNCIGECR.INPUT_INFO.CNTR_ATTR;
        LNRAGRE.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRAGRE.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNRAGRE.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRAGRE.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_WRITE_AGRE();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZGACNO() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-GENERTE-ACNO", BPCCGAC);
        if (BPCCGAC.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCCGAC.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCIGECR.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_AGRE() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNRAGRE.DRAW_NO);
        LNTAGRE_RD = new DBParm();
        LNTAGRE_RD.TableName = "LNTAGRE";
        LNTAGRE_RD.where = "DRAW_NO = :LNRAGRE.DRAW_NO "
            + "AND PAPER_NO = :LNRAGRE.PAPER_NO";
        IBS.READ(SCCGWA, LNRAGRE, this, LNTAGRE_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_CMMT_FLG = 'Y';
        } else {
            WS_CMMT_FLG = 'N';
        }
    }
    public void T000_READUPD_AGRE() throws IOException,SQLException,Exception {
        LNTAGRE_RD = new DBParm();
        LNTAGRE_RD.TableName = "LNTAGRE";
        LNTAGRE_RD.where = "DRAW_NO = :LNRAGRE.DRAW_NO";
        LNTAGRE_RD.upd = true;
        IBS.READ(SCCGWA, LNRAGRE, this, LNTAGRE_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_CMMT_FLG = 'Y';
        } else {
            WS_CMMT_FLG = 'N';
        }
    }
    public void T000_REWRITE_AGRE() throws IOException,SQLException,Exception {
        LNRAGRE.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRAGRE.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNTAGRE_RD = new DBParm();
        LNTAGRE_RD.TableName = "LNTAGRE";
        IBS.REWRITE(SCCGWA, LNRAGRE, LNTAGRE_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void T000_WRITE_AGRE() throws IOException,SQLException,Exception {
        LNRAGRE.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRAGRE.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNRAGRE.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRAGRE.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNTAGRE_RD = new DBParm();
        LNTAGRE_RD.TableName = "LNTAGRE";
        IBS.WRITE(SCCGWA, LNRAGRE, LNTAGRE_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_DUPKEY, LNCIGECR.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (LNCIGECR.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "LNCIGECR=");
            CEP.TRC(SCCGWA, LNCIGECR);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
